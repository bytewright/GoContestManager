package org.bytewright.backend.services;

import java.util.List;

import org.bytewright.backend.dto.Contest;
import org.springframework.stereotype.Component;

@Component
public class ContestService {
  public Contest getContest(String contestId) {
    Contest contest = new Contest();
    contest.setName("JCC - Jena CrossCut" + contestId);
    contest.setIdentifier("jcc" + contestId + "2020");
    return contest;
  }

  public List<Contest> getValidContests() {
    return List.of(getContest("1"), getContest("2"));
  }
}
