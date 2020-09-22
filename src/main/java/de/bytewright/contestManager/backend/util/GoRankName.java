package de.bytewright.contestManager.backend.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

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

  public static Optional<GoRankName> from(String name) {
    return Arrays.stream(GoRankName.values())
        .filter(goRankName -> goRankName.getLetter().equals(name) || goRankName.getName().equals(name))
        .findFirst();
  }
}
