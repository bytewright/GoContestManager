package org.bytewright.backend.dto;

import org.bytewright.backend.util.GoRank;

public class Player extends Person {
  private GoRank goRank;
  private String goClub;
  private boolean isStudent;
  private boolean isSenior;

  public GoRank getGoRank() {
    return goRank;
  }

  public void setGoRank(GoRank goRank) {
    this.goRank = goRank;
  }

  public String getGoClub() {
    return goClub;
  }

  public void setGoClub(String goClub) {
    this.goClub = goClub;
  }

  public boolean isStudent() {
    return isStudent;
  }

  public void setStudent(boolean student) {
    isStudent = student;
  }

  public boolean isSenior() {
    return isSenior;
  }

  public void setSenior(boolean senior) {
    isSenior = senior;
  }
}
