package org.bytewright.backend.util;

import java.io.Serializable;

public enum GoRankName implements Serializable {
  KYU("k", "Kyu"),
  DAN("d", "Dan"),
  PRO("p", "Dan Pro");

  private final String letter;
  private final String name;

  GoRankName(String letter, String name) {
    this.letter = letter;
    this.name = name;
  }

  public String getLetter() {
    return letter;
  }

  public String getName() {
    return name;
  }
}
