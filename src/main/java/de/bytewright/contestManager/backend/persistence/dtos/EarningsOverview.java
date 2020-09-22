package de.bytewright.contestManager.backend.persistence.dtos;

import java.io.Serializable;

public class EarningsOverview implements Serializable {
  private String totalEarnings;
  private String startFeeEarnings;
  private long breakfastCount;
  private String breakfastEarnings;
  private String totalCurrentEarnings;

  public long getBreakfastCount() {
    return breakfastCount;
  }

  public void setBreakfastEarnings(long breakfastCount, String totalBreakfastEarning) {
    this.breakfastCount = breakfastCount;
    this.breakfastEarnings = totalBreakfastEarning;
  }

  public String getTotalEarnings() {
    return totalEarnings;
  }

  public void setTotalEarnings(String totalEarnings) {
    this.totalEarnings = totalEarnings;
  }

  public String getStartFeeEarnings() {
    return startFeeEarnings;
  }

  public void setStartFeeEarnings(String startFeeEarnings) {
    this.startFeeEarnings = startFeeEarnings;
  }

  public String getBreakfastEarnings() {
    return breakfastEarnings;
  }

  public void setBreakfastEarnings(String breakfastEarnings) {
    this.breakfastEarnings = breakfastEarnings;
  }

  public String getTotalCurrentEarnings() {
    return totalCurrentEarnings;
  }

  public void setTotalCurrentEarnings(String totalCurrentEarnings) {
    this.totalCurrentEarnings = totalCurrentEarnings;
  }
}
