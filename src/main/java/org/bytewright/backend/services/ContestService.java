package org.bytewright.backend.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.util.PersonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestService.class);
  private static Random random = new Random();
  private static Map<String, Contest> knownContests = IntStream.range(2020, 2035)
      .mapToObj(value -> "jcc" + value)
      .map(value -> Pair.of(value, createDummyContest(value)))
      .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

  public Optional<Contest> getContest(String contestId) {
    Contest contest = knownContests.get(contestId);
    LOGGER.debug("contest with id {} requested, found: {}", contestId, contest);
    return Optional.ofNullable(contest);
  }

  public List<Contest> getValidContests() {
    Instant now = Instant.now();
    return knownContests.values().stream()
        .filter(contest -> contest.getDateEnd().isAfter(now))
        .collect(Collectors.toList());
  }

  public boolean createContest() {
    return false;
  }

  private static Contest createDummyContest(String uId) {
    Contest contest = new Contest();
    contest.setuId(uId);
    contest.setName("Jena CrossCut - " + uId);
    contest.setDateStart(Instant.now().plus(random.nextInt(400), ChronoUnit.DAYS));
    contest.setDateEnd(contest.getDateStart().plus(random.nextInt(20), ChronoUnit.DAYS));
    Location location = new Location();
    location.setCity("Jena");
    location.setName("Schule abc bla");
    location.setStreet("Some Street");
    location.setStreetNum("1337");
    contest.setLocation(location);
    ContestSettings pSettings = new ContestSettings();
    pSettings.setStartingFee(12);
    contest.setContestSettings(pSettings);
    contest.setOrganisers(Set.of(PersonUtil.rndOrga()));
    contest.setHelpers(Set.of(PersonUtil.rndHelper(), PersonUtil.rndHelper(), PersonUtil.rndHelper()));
    contest.setPlayers(PersonUtil.rndPlayers(30));
    return contest;
  }
}
