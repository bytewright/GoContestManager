package org.bytewright.backend.util;

import java.time.Instant;
import java.util.Optional;

import org.bytewright.backend.dto.Contest;

public class SessionInfo {
  private Instant creationInstant;
  private Contest selectedContest;

  public Instant getCreationInstant() {
    return creationInstant;
  }

  public void setCreationInstant(Instant creationInstant) {
    this.creationInstant = creationInstant;
  }

  public Optional<Contest> getSelectedContest() {
    return Optional.ofNullable(selectedContest);
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
}
