package org.bytewright.backend.util;

import java.io.Serializable;

public enum GoRank implements Serializable {
  KYU_30(30, RankName.KYU, "30k"),
  KYU_29(29, RankName.KYU, "29k"),
  KYU_28(28, RankName.KYU, "28k"),
  KYU_27(27, RankName.KYU, "27k"),
  KYU_26(26, RankName.KYU, "26k"),
  KYU_25(25, RankName.KYU, "25k"),
  KYU_24(24, RankName.KYU, "24k"),
  KYU_23(23, RankName.KYU, "23k"),
  KYU_22(22, RankName.KYU, "22k"),
  KYU_21(21, RankName.KYU, "21k"),
  KYU_20(20, RankName.KYU, "20k"),
  KYU_19(19, RankName.KYU, "19k"),
  KYU_18(18, RankName.KYU, "18k"),
  KYU_17(17, RankName.KYU, "17k"),
  KYU_16(16, RankName.KYU, "16k"),
  KYU_15(15, RankName.KYU, "15k"),
  KYU_14(14, RankName.KYU, "14k"),
  KYU_13(13, RankName.KYU, "13k"),
  KYU_12(12, RankName.KYU, "12k"),
  KYU_11(11, RankName.KYU, "11k"),
  KYU_10(10, RankName.KYU, "10k"),
  KYU_09(9, RankName.KYU, "9k"),
  KYU_08(8, RankName.KYU, "8k"),
  KYU_07(7, RankName.KYU, "7k"),
  KYU_06(6, RankName.KYU, "6k"),
  KYU_05(5, RankName.KYU, "5k"),
  KYU_04(4, RankName.KYU, "4k"),
  KYU_03(3, RankName.KYU, "3k"),
  KYU_02(2, RankName.KYU, "2k"),
  KYU_01(1, RankName.KYU, "1k"),
  DAN_01(1, RankName.DAN, "1d"),
  DAN_02(2, RankName.DAN, "2d"),
  DAN_03(3, RankName.DAN, "3d"),
  DAN_04(5, RankName.DAN, "4d"),
  DAN_05(4, RankName.DAN, "5d"),
  DAN_06(6, RankName.DAN, "6d"),
  DAN_07(7, RankName.DAN, "7d"),
  PRO_01(1, RankName.PRO, "1p"),
  PRO_02(2, RankName.PRO, "2p"),
  PRO_03(3, RankName.PRO, "3p"),
  PRO_04(4, RankName.PRO, "4p"),
  PRO_05(5, RankName.PRO, "5p"),
  PRO_06(6, RankName.PRO, "6p"),
  PRO_07(7, RankName.PRO, "7p"),
  PRO_08(8, RankName.PRO, "8p"),
  PRO_09(9, RankName.PRO, "9p");

  private final int rank;
  private final RankName rankName;
  private final String abbreviation;

  GoRank(int rank, RankName rankName, String abbrv) {
    this.rank = rank;
    this.rankName = rankName;
    abbreviation = abbrv;
  }

  public int getRank() {
    return rank;
  }

  public RankName getRankName() {
    return rankName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}
