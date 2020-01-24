package org.bytewright.backend.services;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.transaction.Transactional;

import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.ContestSettings;
import org.bytewright.backend.persistence.dtos.EarningsOverview;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.persistence.entities.ContestEntity;
import org.bytewright.backend.persistence.repositories.ContestRepository;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.backend.util.PaymentStatus;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestServiceImpl implements ContestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestServiceImpl.class);

  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  @Transactional
  public void saveOrUpdateContestSettings(ContestSettings contestSettings) {
    Contest selectedContest = GoContestManagerSession.get().getContest();
    ContestEntity entity = modelMapper.map(selectedContest, ContestEntity.class);
    LOGGER.info("persisting changes to contest {}: {}", selectedContest, contestSettings);
    contestRepository.save(entity);
  }

  @Override
  @Transactional
  public Optional<Contest> getContest(String contestId) {
    Optional<ContestEntity> entityOptional = contestRepository.findByShortIdentifier(contestId);
    LOGGER.debug("contest with id {} requested, found: {}", contestId, entityOptional);
    return entityOptional.map(contestEntity -> modelMapper.map(contestEntity, Contest.class));
  }

  @Override
  @Transactional
  public List<Contest> getValidContests() {
    ZonedDateTime now = ZonedDateTime.now();
    return contestRepository.findAll().stream().map(contestEntity -> modelMapper.map(contestEntity, Contest.class))
        .filter(contest -> contest.getContestSettings().getDateEnd().isAfter(now))
        .sorted(Comparator.comparing(contest -> contest.getContestSettings().getDateStart()))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public boolean createContest(Contest value) {
    try {
      ContestEntity entity = modelMapper.map(value, ContestEntity.class);
      contestRepository.save(entity);
    } catch (Exception e) {
      LOGGER.error("Failed to save contest {}", value.getUniqueId(), e);
      return false;
    }
    return true;
  }

  @Override
  @Transactional
  public Contest getNextContest() {
    return contestRepository.findFirstByStartUtcTimeIsAfter(Instant.now())
        .map(entity -> modelMapper.map(entity, Contest.class))
        .orElseThrow();
  }

  @Override
  public EarningsOverview getEarningsOverview(Contest contest) {
    MonetaryAmountFormat amountFormat = MonetaryFormats.getAmountFormat(Locale.GERMAN);
    EarningsOverview earningsOverview = new EarningsOverview();
    long breakfastCount = contest.getPlayers().stream()
        .filter(Player::isAttendsBreakfast)
        .count();
    MonetaryAmount totalStartingFees = contest.getPlayers()
        .stream()
        .map(player -> calcStartingFee(contest, player))
        .reduce(MonetaryAmount::add)
        .orElse(Money.zero(contest.getContestSettings().getCurrencyUnit()));
    MonetaryAmount breakfastTotal = contest.getContestSettings().getFeeBreakfast().multiply(breakfastCount);
    earningsOverview.setStartFeeEarnings(amountFormat.format(totalStartingFees));
    earningsOverview.setBreakfastEarnings(breakfastCount, amountFormat.format(breakfastTotal));
    earningsOverview.setTotalEarnings(amountFormat.format(totalStartingFees.add(breakfastTotal)));
    MonetaryAmount currentTotal = contest.getPlayers()
        .stream()
        .filter(player -> player.getPaymentStatus().equals(PaymentStatus.FULLY_PAID))
        .map(player -> calcStartingFee(contest, player))
        .reduce(MonetaryAmount::add)
        .orElse(Money.zero(contest.getContestSettings().getCurrencyUnit()));
    currentTotal = currentTotal.add(
        contest.getContestSettings().getFeeBreakfast()
            .multiply(contest.getPlayers().stream()
                .filter(Player::isAttendsBreakfast)
                .filter(player -> player.getPaymentStatus().equals(PaymentStatus.FULLY_PAID))
                .count()));
    earningsOverview.setTotalCurrentEarnings(amountFormat.format(currentTotal));
    return earningsOverview;
  }

  @Override
  public MonetaryAmount calcStartingFee(Contest contest, Player player) {
    ContestSettings settings = contest.getContestSettings();
    MonetaryAmount feeStart = settings.getFeeStart();
    if (player.isGoClubMember()) {
      feeStart = feeStart.subtract(settings.getDiscountClubMember());
    }
    if (player.isDiscounted()) {
      feeStart = feeStart.subtract(settings.getDiscount());
    }
    if (settings.getStartingFeeFreedRanks().contains(player.getGoRank())) {
      feeStart = Money.zero(settings.getCurrencyUnit());
    }
    return feeStart;
  }

  @Override
  public MonetaryAmount calcTotalPayment(Contest contest, Player player) {
    MonetaryAmount totalFee = calcStartingFee(contest, player);
    if (player.isAttendsBreakfast()) {
      totalFee = totalFee.add(contest.getContestSettings().getFeeBreakfast());
    }
    return totalFee;
  }

  @Override
  @Transactional
  public void update(Contest contest) {
    try {
      ContestEntity entity = modelMapper.map(contest, ContestEntity.class);
      if (entity.getId() == null) {
        throw new IllegalStateException("entity for contest was not found: " + contest.getUniqueId());
      }
      contestRepository.save(entity);
    } catch (Exception e) {
      LOGGER.error("Failed to save contest {}", contest.getUniqueId(), e);
    }
  }

  @Override
  public boolean isValid(Contest contest) {
    ZonedDateTime now = ZonedDateTime.now();
    return !contest.getContestSettings().getDateEnd().isBefore(now);
  }
}
