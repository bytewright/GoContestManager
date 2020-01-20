package org.bytewright.backend.persistence.entities;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bytewright.backend.persistence.converter.GoRanksToString;
import org.bytewright.backend.util.GoRank;

@Entity
@Table(name = "contests", schema = "gcm")
public class ContestEntity extends BaseAuditedEntity {

  @NotBlank
  @Size(min = 3, max = 10)
  @Column(unique = true)
  private String shortIdentifier;
  @NotBlank
  @Size(min = 1, max = 4)
  private String currencyUnitCode;
  private double discountGeneralAmount;
  private double discountClubAmount;
  private double discountPreRegAmount;
  private double feeStartAmount;
  private double feeBreakfastAmount;
  private int roundCount;
  @NotBlank
  @Size(min = 3, max = 100)
  private String contestName;
  @NotNull
  @Convert(converter = GoRanksToString.class)
  private Set<GoRank> startingFeeFreedRanks;
  @NotBlank
  private String timeZone;
  @NotNull
  private Instant startUtcTime;
  @NotNull
  private Instant endUtcTime;
  @Embedded
  private LocationEmbeddable location;

  @Override
  public String toString() {
    return "ContestEntity{" +
        "shortIdentifier='" + shortIdentifier + '\'' +
        ", currencyUnitCode='" + currencyUnitCode + '\'' +
        ", discountGeneralAmount=" + discountGeneralAmount +
        ", discountClubAmount=" + discountClubAmount +
        ", discountPreRegAmount=" + discountPreRegAmount +
        ", feeStartAmount=" + feeStartAmount +
        ", feeBreakfastAmount=" + feeBreakfastAmount +
        ", roundCount=" + roundCount +
        ", contestName='" + contestName + '\'' +
        ", startingFeeFreedRanks=" + startingFeeFreedRanks +
        ", timeZone='" + timeZone + '\'' +
        ", startUtcTime=" + startUtcTime +
        ", endUtcTime=" + endUtcTime +
        ", location=" + location +
        ", id=" + id +
        ", lastModified=" + lastModified +
        '}';
  }

  public String getShortIdentifier() {
    return shortIdentifier;
  }

  public void setShortIdentifier(String shortIdentifier) {
    this.shortIdentifier = shortIdentifier;
  }

  public String getCurrencyUnitCode() {
    return currencyUnitCode;
  }

  public void setCurrencyUnitCode(String currencyUnitCode) {
    this.currencyUnitCode = currencyUnitCode;
  }

  public double getDiscountGeneralAmount() {
    return discountGeneralAmount;
  }

  public void setDiscountGeneralAmount(double discountGeneralAmount) {
    this.discountGeneralAmount = discountGeneralAmount;
  }

  public double getDiscountClubAmount() {
    return discountClubAmount;
  }

  public void setDiscountClubAmount(double discountClubAmount) {
    this.discountClubAmount = discountClubAmount;
  }

  public double getDiscountPreRegAmount() {
    return discountPreRegAmount;
  }

  public void setDiscountPreRegAmount(double discountPreRegAmount) {
    this.discountPreRegAmount = discountPreRegAmount;
  }

  public double getFeeStartAmount() {
    return feeStartAmount;
  }

  public void setFeeStartAmount(double feeStartAmount) {
    this.feeStartAmount = feeStartAmount;
  }

  public double getFeeBreakfastAmount() {
    return feeBreakfastAmount;
  }

  public void setFeeBreakfastAmount(double feeBreakfastAmount) {
    this.feeBreakfastAmount = feeBreakfastAmount;
  }

  public int getRoundCount() {
    return roundCount;
  }

  public void setRoundCount(int roundCount) {
    this.roundCount = roundCount;
  }

  public String getContestName() {
    return contestName;
  }

  public void setContestName(String contestName) {
    this.contestName = contestName;
  }

  public Set<GoRank> getStartingFeeFreedRanks() {
    return startingFeeFreedRanks;
  }

  public void setStartingFeeFreedRanks(Set<GoRank> startingFeeFreedRanks) {
    this.startingFeeFreedRanks = startingFeeFreedRanks;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public Instant getStartUtcTime() {
    return startUtcTime;
  }

  public void setStartUtcTime(Instant startUtcTime) {
    this.startUtcTime = startUtcTime;
  }

  public Instant getEndUtcTime() {
    return endUtcTime;
  }

  public void setEndUtcTime(Instant endUtcTime) {
    this.endUtcTime = endUtcTime;
  }

  public LocationEmbeddable getLocation() {
    return location;
  }

  public void setLocation(LocationEmbeddable location) {
    this.location = location;
  }
}
