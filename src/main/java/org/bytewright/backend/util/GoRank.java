package org.bytewright.backend.util;

import java.io.Serializable;

public enum GoRank implements Serializable {
  KYU_30(30, "Kyu", "30k"),
  //...
  KYU_01(1, "K", "1k"),
  DAN_01(1, "Dan", "1d"),
  //...
  DAN_07(7, "Dan", "7d"),
  PRO_01(1, "Dan Pro", "1p"),
  //...
  PRO_09(9, "Dan Pro", "9p");

  private final int rank;
  private final String letter;
  private String abbriviation;

  private GoRank(int rank, String letter, String abbrv) {

    this.rank = rank;
    this.letter = letter;
    abbriviation = abbrv;
  }
}
