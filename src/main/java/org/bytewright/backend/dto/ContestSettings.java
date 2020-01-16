package org.bytewright.backend.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

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

  private ZonedDateTime dateEnd;
  private ZonedDateTime dateStart;
  private int roundCount;
  private String name;
  private Location location;
  private Set<GoRank> startingFeeFreedRanks;

  public ContestSettings() {
    /* Default settings */
    currencyUnit = Monetary.getCurrency("EUR");
    feeStart = Money.of(12.00, currencyUnit);
    discountClubMember = Money.of(4.00, currencyUnit);
    discount = Money.of(4.00, currencyUnit);
    discountPreRegistered = Money.of(4.00, currencyUnit);
    feeBreakfast = Money.of(6.50, currencyUnit);
    roundCount = 5;
    startingFeeFreedRanks = Set.of(GoRank.KYU_30);
    dateEnd = ZonedDateTime.now();
    dateStart = ZonedDateTime.now();
    name = "Go contest";
    location = new Location();
  }

  public ZonedDateTime getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(ZonedDateTime dateEnd) {
    this.dateEnd = dateEnd;
  }

  public ZonedDateTime getDateStart() {
    return dateStart;
  }

  public void setDateStart(ZonedDateTime dateStart) {
    this.dateStart = dateStart;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public MonetaryAmount getDiscount() {
    return discount;
  }

  public void setDiscount(MonetaryAmount discount) {
    this.discount = discount;
  }

  public void setDiscount(double discount) {
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

  public void setDiscountClubMember(double discountClubMember) {
    this.discountClubMember = Money.of(discountClubMember, currencyUnit);
  }

  public MonetaryAmount getDiscountPreRegistered() {
    return discountPreRegistered;
  }

  public void setDiscountPreRegistered(MonetaryAmount discountPreRegistered) {
    this.discountPreRegistered = discountPreRegistered;
  }

  public void setDiscountPreRegistered(double discountPreRegistered) {
    this.discountPreRegistered = Money.of(discountPreRegistered, currencyUnit);
  }

  public MonetaryAmount getFeeBreakfast() {
    return feeBreakfast;
  }

  public void setFeeBreakfast(MonetaryAmount feeBreakfast) {
    this.feeBreakfast = feeBreakfast;
  }

  public void setFeeBreakfast(double feeBreakfast) {
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

  public void setFeeStart(double feeStart) {
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
    MonetaryAmountFormat amountFormat = MonetaryFormats.getAmountFormat(Locale.GERMAN);
    return "ContestSettings{" +
        "discount=" + amountFormat.format(discount) +
        ", discountClubMember=" + amountFormat.format(discountClubMember) +
        ", discountPreRegistered=" + amountFormat.format(discountPreRegistered) +
        ", feeBreakfast=" + amountFormat.format(feeBreakfast) +
        ", feeStart=" + amountFormat.format(feeStart) +
        ", currencyUnit=" + currencyUnit +
        ", roundCount=" + roundCount +
        ", startingFeeFreedRanks=" + startingFeeFreedRanks +
        '}';
  }
}
