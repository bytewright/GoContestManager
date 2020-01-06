package org.bytewright.backend.dto;

import java.io.Serializable;
import java.util.Set;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.bytewright.backend.util.GoRank;
import org.javamoney.moneta.Money;

public class ContestSettings implements Serializable {
  /** Means student, senior, without work, pupil,... */
  private MonetaryAmount discount;
  private MonetaryAmount discountClubMember;
  private MonetaryAmount discountPreRegistered;
  private MonetaryAmount feeBreakfast;
  private MonetaryAmount feeStart;
  private CurrencyUnit currencyUnit;

  private int roundCount;
  private Set<GoRank> startingFeeFreedRanks;

  public ContestSettings() {
    /* Default settings */
    currencyUnit = Monetary.getCurrency("EUR");
    feeStart = Money.of(1200, currencyUnit);
    discountClubMember = Money.of(400, currencyUnit);
    discount = Money.of(400, currencyUnit);
    discountPreRegistered = Money.of(400, currencyUnit);
    feeBreakfast = Money.of(650, currencyUnit);
    roundCount = 5;
    startingFeeFreedRanks = Set.of(GoRank.KYU_30);
  }

  public MonetaryAmount getDiscount() {
    return discount;
  }

  public void setDiscount(MonetaryAmount discount) {
    this.discount = discount;
  }

  public void setDiscount(int discount) {
    this.discount = Money.of(discount, currencyUnit);
  }

  public CurrencyUnit getCurrencyUnit() {
    return currencyUnit;
  }

  public void setCurrencyUnit(CurrencyUnit currencyUnit) {
    this.currencyUnit = currencyUnit;
  }

  public MonetaryAmount getDiscountClubMember() {
    return discountClubMember;
  }

  public void setDiscountClubMember(MonetaryAmount discountClubMember) {
    this.discountClubMember = discountClubMember;
  }

  public void setDiscountClubMember(int discountClubMember) {
    this.discountClubMember = Money.of(discountClubMember, currencyUnit);
  }

  public MonetaryAmount getDiscountPreRegistered() {
    return discountPreRegistered;
  }

  public void setDiscountPreRegistered(MonetaryAmount discountPreRegistered) {
    this.discountPreRegistered = discountPreRegistered;
  }

  public void setDiscountPreRegistered(int discountPreRegistered) {
    this.discountPreRegistered = Money.of(discountPreRegistered, currencyUnit);
  }

  public MonetaryAmount getFeeBreakfast() {
    return feeBreakfast;
  }

  public void setFeeBreakfast(MonetaryAmount feeBreakfast) {
    this.feeBreakfast = feeBreakfast;
  }

  public void setFeeBreakfast(int feeBreakfast) {
    this.feeBreakfast = Money.of(feeBreakfast, currencyUnit);
  }

  public int getRoundCount() {
    return roundCount;
  }

  public void setRoundCount(int roundCount) {
    this.roundCount = roundCount;
  }

  public MonetaryAmount getFeeStart() {
    return feeStart;
  }

  public void setFeeStart(MonetaryAmount feeStart) {
    this.feeStart = feeStart;
  }

  public void setFeeStart(int feeStart) {
    this.feeStart = Money.of(feeStart, currencyUnit);
  }

  public Set<GoRank> getStartingFeeFreedRanks() {
    return startingFeeFreedRanks;
  }

  public void setStartingFeeFreedRanks(Set<GoRank> startingFeeFreedRanks) {
    this.startingFeeFreedRanks = startingFeeFreedRanks;
  }

  @Override
  public String toString() {
    return "ContestSettings{" +
        "discount=" + discount +
        ", discountClubMember=" + discountClubMember +
        ", discountPreRegistered=" + discountPreRegistered +
        ", feeBreakfast=" + feeBreakfast +
        ", feeStart=" + feeStart +
        ", currencyUnit=" + currencyUnit +
        ", roundCount=" + roundCount +
        ", startingFeeFreedRanks=" + startingFeeFreedRanks +
        '}';
  }
}
