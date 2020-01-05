package org.bytewright.backend.services;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.util.PersonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ContestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestService.class);
  private static Random random = new Random();
  private static Map<String, Contest> knownContests = IntStream.range(2020, 2035)
    .mapToObj(value -> "jcc" + value)
    .map(value -> Pair.of(value, createDummyContest(value)))
    .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

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
}
