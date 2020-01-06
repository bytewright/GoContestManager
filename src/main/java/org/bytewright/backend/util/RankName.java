package org.bytewright.backend.util;

public enum RankName {
  KYU("k", "Kyu"),
  DAN("d", "Dan"),
  PRO("p", "Dan Pro");

  private final String letter;
  private final String name;

  RankName(String letter, String name) {
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
