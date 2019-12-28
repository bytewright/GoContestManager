package org.bytewright.backend.util;

import java.io.Serializable;

public enum GoRank implements Serializable {
  KYU_30(30, "Kyu", "30k"),
  KYU_29(29, "Kyu", "29k"),
  KYU_28(28, "Kyu", "28k"),
  KYU_27(27, "Kyu", "27k"),
  KYU_26(26, "Kyu", "26k"),
  KYU_25(25, "Kyu", "25k"),
  KYU_24(24, "Kyu", "24k"),
  KYU_23(23, "Kyu", "23k"),
  KYU_22(22, "Kyu", "22k"),
  KYU_21(21, "Kyu", "21k"),
  KYU_20(20, "Kyu", "20k"),
  KYU_19(19, "Kyu", "19k"),
  KYU_18(18, "Kyu", "18k"),
  KYU_17(17, "Kyu", "17k"),
  KYU_16(16, "Kyu", "16k"),
  KYU_15(15, "Kyu", "15k"),
  KYU_14(14, "Kyu", "14k"),
  KYU_13(13, "Kyu", "13k"),
  KYU_12(12, "Kyu", "12k"),
  KYU_11(11, "Kyu", "11k"),
  KYU_10(10, "Kyu", "10k"),
  KYU_09(9, "Kyu", "9k"),
  KYU_08(8, "Kyu", "8k"),
  KYU_07(7, "Kyu", "7k"),
  KYU_06(6, "Kyu", "6k"),
  KYU_05(5, "Kyu", "5k"),
  KYU_04(4, "Kyu", "4k"),
  KYU_03(3, "Kyu", "3k"),
  KYU_02(2, "Kyu", "2k"),
  KYU_01(1, "Kyu", "1k"),
  DAN_01(1, "Dan", "1d"),
  DAN_02(2, "Dan", "2d"),
  DAN_03(3, "Dan", "3d"),
  DAN_04(5, "Dan", "4d"),
  DAN_05(4, "Dan", "5d"),
  DAN_06(6, "Dan", "6d"),
  DAN_07(7, "Dan", "7d"),
  PRO_01(1, "Dan Pro", "1p"),
  PRO_02(2, "Dan Pro", "2p"),
  PRO_03(3, "Dan Pro", "3p"),
  PRO_04(4, "Dan Pro", "4p"),
  PRO_05(5, "Dan Pro", "5p"),
  PRO_06(6, "Dan Pro", "6p"),
  PRO_07(7, "Dan Pro", "7p"),
  PRO_08(8, "Dan Pro", "8p"),
  PRO_09(9, "Dan Pro", "9p");

  private final int rank;
  private final String letter;
  private String abbreviation;

  private GoRank(int rank, String letter, String abbrv) {
    this.rank = rank;
    this.letter = letter;
    abbreviation = abbrv;
  }

  public int getRank() {
    return rank;
  }

  public String getLetter() {
    return letter;
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}
