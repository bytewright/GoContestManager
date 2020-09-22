package de.bytewright.contestManager.backend.services;

import java.util.List;
import java.util.Optional;

import javax.money.MonetaryAmount;
import javax.transaction.Transactional;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.ContestSettings;
import de.bytewright.contestManager.backend.persistence.dtos.EarningsOverview;
import de.bytewright.contestManager.backend.persistence.dtos.Player;

public interface ContestService {
  @Transactional
  void saveOrUpdateContestSettings(ContestSettings contestSettings);

  @Transactional
  Optional<Contest> getContest(String contestId);

  @Transactional
  List<Contest> getValidContests();

  @Transactional
  boolean createContest(Contest value);

  @Transactional
  Contest getNextContest();

  EarningsOverview getEarningsOverview(Contest contest);

  MonetaryAmount calcStartingFee(Contest contest, Player player);

  MonetaryAmount calcTotalPayment(Contest contest, Player player);

  @Transactional
  void update(Contest contest);

  boolean isValid(Contest contest);
}
