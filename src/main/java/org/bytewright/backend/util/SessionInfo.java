package org.bytewright.backend.util;

import org.bytewright.backend.dto.Contest;

import java.time.Instant;
import java.util.Optional;

public class SessionInfo {
  private Instant creationInstant;
  private Contest selectedContest;

  public Instant getCreationInstant() {
    return creationInstant;
  }

  public void setCreationInstant(Instant creationInstant) {
    this.creationInstant = creationInstant;
  }

  public Contest getSelectedContest() {
    if (selectedContest == null) {
      throw new IllegalStateException("Selected contest is not set!");
    }
    return selectedContest;
  }

  public void setSelectedContest(Contest selectedContest) {
    this.selectedContest = selectedContest;
  }

  @Override
  public String toString() {
    return "SessionInfo{" +
      "creationInstant=" + creationInstant +
      ", selectedContest=" + selectedContest +
      '}';
  }

  public Optional<Contest> getSelectedContestOpt() {
    return Optional.ofNullable(selectedContest);
  }
}
