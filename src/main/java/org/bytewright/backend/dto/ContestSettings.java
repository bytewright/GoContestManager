package org.bytewright.backend.dto;

import org.bytewright.backend.util.GoRank;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.util.Set;

public class ContestSettings implements Serializable {
  private CurrencyUnit currencyUnit;
  private MonetaryAmount discountClubMember;
  private MonetaryAmount discountSenior;
  private MonetaryAmount discountStudent;
  private int roundCount;
  private MonetaryAmount startingFee;
  private Set<GoRank> startingFeeFreedRanks;

  public ContestSettings() {
    /* Default settings */
    currencyUnit = Monetary.getCurrency("EUR");
    startingFee = Money.of(1200, currencyUnit);
    discountClubMember = Money.of(400, currencyUnit);
    discountSenior = Money.of(700, currencyUnit);
    discountStudent = Money.of(650, currencyUnit);
    roundCount = 5;
    startingFeeFreedRanks = Set.of(GoRank.KYU_30);
  }

  public int getDiscountClubMemberAmount() {
    return discountClubMember.getNumber().intValueExact();
  }

  public void setDiscountClubMemberAmount(int discountClubMember) {
    this.discountClubMember = Money.of(discountClubMember, currencyUnit);
  }

  public int getDiscountSeniorAmount() {
    return discountSenior.getNumber().intValueExact();
  }

  public void setDiscountSeniorAmount(int discountSenior) {
    this.discountSenior = Money.of(discountSenior, currencyUnit);
  }

  public int getDiscountStudentAmount() {
    return discountStudent.getNumber().intValueExact();
  }

  public void setDiscountStudentAmount(int discountStudent) {
    this.discountStudent = Money.of(discountStudent, currencyUnit);
  }

  public Set<GoRank> getStartingFeeFreedRanks() {
    return startingFeeFreedRanks;
  }

  public void setStartingFeeFreedRanks(Set<GoRank> startingFeeFreedRanks) {
    this.startingFeeFreedRanks = startingFeeFreedRanks;
  }

  public CurrencyUnit getCurrencyUnit() {
    return currencyUnit;
  }

  public void setCurrencyUnit(CurrencyUnit currencyUnit) {
    this.currencyUnit = currencyUnit;
  }

  public int getRoundCount() {
    return roundCount;
  }

  public void setRoundCount(int roundCount) {
    this.roundCount = roundCount;
  }

  public MonetaryAmount getStartingFee() {
    return startingFee;
  }

  public void setStartingFee(MonetaryAmount startingFee) {
    this.startingFee = startingFee;
  }

  public int getStartingFeeAmount() {
    return startingFee.getNumber().intValueExact();
  }

  public void setStartingFeeAmount(int startingFee) {
    this.startingFee = Money.of(startingFee, currencyUnit);
  }
}
