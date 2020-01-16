package org.bytewright.backend.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum GoRank implements Serializable {
  KYU_30(30, GoRankName.KYU, "30k"),
  KYU_29(29, GoRankName.KYU, "29k"),
  KYU_28(28, GoRankName.KYU, "28k"),
  KYU_27(27, GoRankName.KYU, "27k"),
  KYU_26(26, GoRankName.KYU, "26k"),
  KYU_25(25, GoRankName.KYU, "25k"),
  KYU_24(24, GoRankName.KYU, "24k"),
  KYU_23(23, GoRankName.KYU, "23k"),
  KYU_22(22, GoRankName.KYU, "22k"),
  KYU_21(21, GoRankName.KYU, "21k"),
  KYU_20(20, GoRankName.KYU, "20k"),
  KYU_19(19, GoRankName.KYU, "19k"),
  KYU_18(18, GoRankName.KYU, "18k"),
  KYU_17(17, GoRankName.KYU, "17k"),
  KYU_16(16, GoRankName.KYU, "16k"),
  KYU_15(15, GoRankName.KYU, "15k"),
  KYU_14(14, GoRankName.KYU, "14k"),
  KYU_13(13, GoRankName.KYU, "13k"),
  KYU_12(12, GoRankName.KYU, "12k"),
  KYU_11(11, GoRankName.KYU, "11k"),
  KYU_10(10, GoRankName.KYU, "10k"),
  KYU_09(9, GoRankName.KYU, "9k"),
  KYU_08(8, GoRankName.KYU, "8k"),
  KYU_07(7, GoRankName.KYU, "7k"),
  KYU_06(6, GoRankName.KYU, "6k"),
  KYU_05(5, GoRankName.KYU, "5k"),
  KYU_04(4, GoRankName.KYU, "4k"),
  KYU_03(3, GoRankName.KYU, "3k"),
  KYU_02(2, GoRankName.KYU, "2k"),
  KYU_01(1, GoRankName.KYU, "1k"),
  DAN_01(1, GoRankName.DAN, "1d"),
  DAN_02(2, GoRankName.DAN, "2d"),
  DAN_03(3, GoRankName.DAN, "3d"),
  DAN_04(5, GoRankName.DAN, "4d"),
  DAN_05(4, GoRankName.DAN, "5d"),
  DAN_06(6, GoRankName.DAN, "6d"),
  DAN_07(7, GoRankName.DAN, "7d"),
  PRO_01(1, GoRankName.PRO, "1p"),
  PRO_02(2, GoRankName.PRO, "2p"),
  PRO_03(3, GoRankName.PRO, "3p"),
  PRO_04(4, GoRankName.PRO, "4p"),
  PRO_05(5, GoRankName.PRO, "5p"),
  PRO_06(6, GoRankName.PRO, "6p"),
  PRO_07(7, GoRankName.PRO, "7p"),
  PRO_08(8, GoRankName.PRO, "8p"),
  PRO_09(9, GoRankName.PRO, "9p");

  private final int rank;
  private final GoRankName goRankName;
  private final String abbreviation;

  GoRank(int rank, GoRankName goRankName, String abbrv) {
    this.rank = rank;
    this.goRankName = goRankName;
    abbreviation = abbrv;
  }

  public int getRank() {
    return rank;
  }

  public GoRankName getGoRankName() {
    return goRankName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public static Optional<GoRank> from(Integer rankNum, GoRankName goRankName) {
    return Arrays.stream(GoRank.values())
        .filter(goRank -> goRankName.equals(goRank.getGoRankName()))
        .filter(goRank -> rankNum == goRank.getRank())
        .findFirst();
  }

  public static GoRank fromAbbrv(String abbrv) {
    return Arrays.stream(GoRank.values())
        .filter(goRank -> goRank.getAbbreviation().equalsIgnoreCase(abbrv))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("There is no GoRank " + abbrv));
  }
}
