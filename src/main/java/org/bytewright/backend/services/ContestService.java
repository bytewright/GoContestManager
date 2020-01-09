package org.bytewright.backend.services;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.EarningsOverview;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.util.PaymentStatus;
import org.bytewright.backend.util.PersonUtil;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestService.class);
  private static Random random = new Random();
  private static Map<String, Contest> knownContests = IntStream.range(2020, 2035)
      .mapToObj(value -> "jcc" + value)
      .map(value -> Pair.of(value, createDummyContest(value)))
      .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
  @Autowired
  private UserService userService;

  public void saveOrUpdateContestSettings(ContestSettings contestSettings) {
    Contest selectedContest = userService.getSessionInfo().getSelectedContest();
    LOGGER.info("persisting changes to contest {}: {}", selectedContest, contestSettings);
  }

  public Optional<Contest> getContest(String contestId) {
    Contest contest = knownContests.get(contestId);
    LOGGER.debug("contest with id {} requested, found: {}", contestId, contest);
    return Optional.ofNullable(contest);
  }

  public List<Contest> getValidContests() {
    ZonedDateTime now = ZonedDateTime.now();
    return knownContests.values().stream()
        .filter(contest -> contest.getDateEnd().isAfter(now))
        .sorted(Comparator.comparing(Contest::getDateStart))
        .collect(Collectors.toList());
  }

  public boolean createContest() {
    return false;
  }

  public Contest getNextContest() {
    return getValidContests().get(0);
  }

  public MonetaryAmount getTotalEarnings(Contest contest) {
    return Money.of(2222, contest.getContestSettings().getCurrencyUnit());
  }

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

  public MonetaryAmount calcTotalPayment(Contest contest, Player player) {
    MonetaryAmount totalFee = calcStartingFee(contest, player);
    if (player.isAttendsBreakfast()) {
      totalFee = totalFee.add(contest.getContestSettings().getFeeBreakfast());
    }
    return totalFee;
  }

  private static Contest createDummyContest(String uId) {
    Contest contest = new Contest();
    contest.setuId(uId);
    contest.setName("Jena CrossCut - " + uId);
    contest.setDateStart(ZonedDateTime.now().plus(random.nextInt(400), ChronoUnit.DAYS));
    contest.setDateEnd(contest.getDateStart().plus(random.nextInt(20), ChronoUnit.DAYS));
    Location location = new Location();
    location.setCity("Jena");
    location.setName("Schule abc bla");
    location.setStreet("Some Street");
    location.setStreetNum("1337");
    contest.setLocation(location);
    ContestSettings contestSettings = new ContestSettings();
    contest.setContestSettings(contestSettings);
    contest.setOrganisers(Set.of(PersonUtil.rndOrga()));
    contest.setHelpers(Set.of(PersonUtil.rndHelper(), PersonUtil.rndHelper(), PersonUtil.rndHelper()));
    contest.setPlayers(PersonUtil.rndPlayers(30));
    return contest;
  }
}
