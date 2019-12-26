package org.bytewright.backend.dto;

public class ParticipationSettings {
  //TODO:
  // import javax.money.CurrencyUnit;
  // import javax.money.Monetary;
  // import javax.money.MonetaryAmount;
  //private MonetaryAmount startingFee;
  private Integer startingFee;

  public Integer getStartingFee() {
    return startingFee;
  }

  public void setStartingFee(Integer startingFee) {
    this.startingFee = startingFee;
  }
}
