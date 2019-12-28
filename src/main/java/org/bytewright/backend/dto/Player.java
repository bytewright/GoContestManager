package org.bytewright.backend.dto;

import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;

public class Player extends Person {
  private int age;
  private String goClub;
  private GoRank goRank;
  private boolean isFemale;
  private boolean isSenior;
  private boolean isStudent;
  private boolean isU10;
  private PaymentStatus paymentStatus;

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isFemale() {
    return isFemale;
  }

  public void setFemale(boolean female) {
    isFemale = female;
  }

  public String getGoClub() {
    return goClub;
  }

  public void setGoClub(String goClub) {
    this.goClub = goClub;
  }

  public GoRank getGoRank() {
    return goRank;
  }

  public void setGoRank(GoRank goRank) {
    this.goRank = goRank;
  }

  public boolean isSenior() {
    return isSenior;
  }

  public void setSenior(boolean senior) {
    isSenior = senior;
  }

  public boolean isStudent() {
    return isStudent;
  }

  public void setStudent(boolean student) {
    isStudent = student;
  }

  public boolean isU10() {
    return isU10;
  }

  public void setU10(boolean u10) {
    isU10 = u10;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }
}
