package org.bytewright.backend.dto;

import java.io.Serializable;

public class ContestSettings implements Serializable {
  private int roundCount;
  //TODO:
  // import javax.money.CurrencyUnit;
  // import javax.money.Monetary;
  // import javax.money.MonetaryAmount;
  //private MonetaryAmount startingFee;
  private Integer startingFee;

  public int getRoundCount() {
    return roundCount;
  }

  public void setRoundCount(int roundCount) {
    this.roundCount = roundCount;
  }

  public Integer getStartingFee() {
    return startingFee;
  }

  public void setStartingFee(Integer startingFee) {
    this.startingFee = startingFee;
  }
}
