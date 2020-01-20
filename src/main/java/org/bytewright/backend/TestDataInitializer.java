package org.bytewright.backend;

import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.util.PersonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestDataInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestDataInitializer.class);
  @Autowired
  private ContestService contestService;
  private Random rnd = new SecureRandom();

  @EventListener
  @Async
  public void onContextRefreshedEvent(ContextRefreshedEvent event) {
    LOGGER.warn("Creating fake data for testing");
    createContests();
  }

  private void createContests() {
    Map<String, Contest> contestMap = IntStream.range(2020, 2035)
        .mapToObj(value -> "jcc" + value)
        .map(value -> Pair.of(value, createDummyContest(value)))
        .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    LOGGER.debug("Creating {} contest... Ids: {}", contestMap.size(), String.join(",", contestMap.keySet()));
    for (Map.Entry<String, Contest> entry : contestMap.entrySet()) {
      contestService.createContest(entry.getValue());
    }

  }

  private Contest createDummyContest(String uId) {
    Contest contest = new Contest();
    contest.setuId(uId);
    Location location = new Location();
    location.setCity("Jena");
    location.setName("Schule abc bla");
    location.setStreet("Some Street");
    location.setStreetNum("1337");
    ContestSettings contestSettings = new ContestSettings();
    contestSettings.setName("Jena CrossCut - " + uId);
    contestSettings.setDateStart(ZonedDateTime.now().plus(rnd.nextInt(400), ChronoUnit.DAYS));
    contestSettings.setDateEnd(contestSettings.getDateStart().plus(rnd.nextInt(20), ChronoUnit.DAYS));
    contestSettings.setLocation(location);
    contest.setContestSettings(contestSettings);
    contest.setOrganisers(Set.of(PersonUtil.rndOrga()));
    contest.setHelpers(Set.of(PersonUtil.rndHelper(), PersonUtil.rndHelper(), PersonUtil.rndHelper()));
    contest.setPlayers(PersonUtil.rndPlayers(30));
    return contest;
  }
}
