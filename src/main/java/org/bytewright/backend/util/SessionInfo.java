package org.bytewright.backend.util;

import java.util.Optional;

import org.apache.wicket.Session;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.security.GoContestManagerSession;

public class SessionInfo {

  public static Contest getSSelectedContest() {
    return getSSelectedContestOpt().orElseThrow(() -> new IllegalStateException("Selected contest is not set!"));
  }

  public static void setSSelectedContest(Contest selectedContest) {
    GoContestManagerSession session = (GoContestManagerSession) Session.get();
    session.setContest(selectedContest);
  }

  public static Optional<Contest> getSSelectedContestOpt() {
    GoContestManagerSession session = (GoContestManagerSession) Session.get();
    Contest selectedContest = session.getContest();
    return Optional.ofNullable(selectedContest);
  }
}
