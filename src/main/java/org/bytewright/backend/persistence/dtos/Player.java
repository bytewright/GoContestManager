package org.bytewright.backend.persistence.dtos;

import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;

public class Player extends Person {
  private int age;
  private String goClub;
  private String city;
  private GoRank goRank;
  private PaymentStatus paymentStatus;
  private String registrationFormMessage;
  private String country; // todo must be "internet code"
  private boolean isNeedsSleepOver;
  private boolean isAttendsBreakfast;
  private boolean isGoClubMember;
  private boolean isDiscounted;
  private boolean isFemale;
  private boolean isSenior;
  private boolean isStudent;
  private boolean isU10;
  private boolean isFirstContest;
  private boolean isSeminarMember;

  public boolean isAttendsBreakfast() {
    return isAttendsBreakfast;
  }

  public void setAttendsBreakfast(Boolean aBoolean) {
    isAttendsBreakfast = aBoolean;
  }

  public boolean isGoClubMember() {
    return isGoClubMember;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGoClub() {
    return goClub;
  }

  public void setGoClub(String goClub) {
    this.goClub = goClub;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public GoRank getGoRank() {
    return goRank;
  }

  public void setGoRank(GoRank goRank) {
    this.goRank = goRank;
  }

  public boolean isFemale() {
    return isFemale;
  }

  public void setFemale(boolean female) {
    isFemale = female;
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

  public boolean isFirstContest() {
    return isFirstContest;
  }

  public void setFirstContest(boolean firstContest) {
    isFirstContest = firstContest;
  }

  public boolean isSeminarMember() {
    return isSeminarMember;
  }

  public void setSeminarMember(boolean seminarMember) {
    isSeminarMember = seminarMember;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public boolean isNeedsSleepOver() {
    return isNeedsSleepOver;
  }

  public void setNeedsSleepOver(boolean needsSleepOver) {
    isNeedsSleepOver = needsSleepOver;
  }

  public String getRegistrationFormMessage() {
    return registrationFormMessage;
  }

  public void setRegistrationFormMessage(String registrationFormMessage) {
    this.registrationFormMessage = registrationFormMessage;
  }

  @Override
  public String toString() {
    return "Player{" +
        super.toString() +
        ", age=" + age +
        ", goClub='" + goClub + '\'' +
        ", city='" + city + '\'' +
        ", goRank=" + goRank +
        ", isFemale=" + isFemale +
        ", isSenior=" + isSenior +
        ", isStudent=" + isStudent +
        ", isU10=" + isU10 +
        ", isFirstContest=" + isFirstContest +
        ", isSeminarMember=" + isSeminarMember +
        ", paymentStatus=" + paymentStatus +
        ", isNeedsSleepOver=" + isNeedsSleepOver +
        ", registrationFormMessage='" + registrationFormMessage + '\'' +
        '}';
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setGoClubMember(Boolean aBoolean) {
    isGoClubMember = aBoolean;
  }

  public boolean isDiscounted() {
    return isDiscounted;
  }

  public void setDiscounted(Boolean aBoolean) {
    isDiscounted = aBoolean;
  }
}
