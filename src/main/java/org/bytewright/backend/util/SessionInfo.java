package org.bytewright.backend.util;

import org.bytewright.backend.dto.Contest;

import java.time.Instant;

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
}
