package org.bytewright.backend.services;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.dto.ParticipationSettings;
import org.bytewright.backend.util.PersonUtil;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class ContestService {
  private Random random = new Random();

  public Contest getContest(String contestId) {
    Contest contest = new Contest();
    contest.setIdentifier("jcc" + contestId + "2020");
    contest.setName("JCC - Jena CrossCut" + contestId);
    contest.setStartDate(Instant.now().plus(random.nextLong(), ChronoUnit.MONTHS));
    contest.setEndDate(contest.getStartDate().plus(random.nextLong(), ChronoUnit.DAYS));
    Location location = new Location();
    location.setCity("Jena");
    location.setName("Schule abc bla");
    location.setStreet("Some Street");
    location.setStreetNum("1337");
    contest.setLocation(location);
    ParticipationSettings pSettings = new ParticipationSettings();
    pSettings.setStartingFee(12);
    contest.setParticipationSettings(pSettings);
    contest.setOrganisers(Set.of(PersonUtil.rndOrga()));
    contest.setHelpers(Set.of(PersonUtil.rndHelper(), PersonUtil.rndHelper(), PersonUtil.rndHelper()));
    contest.setPlayers(PersonUtil.rndPlayers(30));
    return contest;
  }

  public List<Contest> getValidContests() {
    return List.of(getContest("1"), getContest("2"));
  }
}
