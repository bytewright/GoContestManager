package org.bytewright.backend.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bytewright.backend.persistence.converter.GoRankToString;
import org.bytewright.backend.persistence.converter.PaymentStatusToString;
import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;

@Entity
@Table(name = "players", schema = "gcm")
public class PlayerEntity extends AbstractPersonEntity {
  private int age;
  @NotBlank
  @Size(max = 255)
  private String city;
  @NotBlank
  @Size(max = 255)
  private String goClub;
  @NotBlank
  @Size(max = 255)
  private String country;
  @NotNull
  @Convert(converter = GoRankToString.class)
  private GoRank goRank;
  @NotNull
  @Convert(converter = PaymentStatusToString.class)
  private PaymentStatus paymentStatus;
  private String registrationFormMessage;

  @Column(name = "needs_sleep_over")
  private boolean isNeedsSleepOver;
  @Column(name = "attends_breakfast")
  private boolean isAttendsBreakfast;
  @Column(name = "go_club_member")
  private boolean isGoClubMember;
  @Column(name = "discounted")
  private boolean isDiscounted;
  @Column(name = "female")
  private boolean isFemale;
  @Column(name = "senior")
  private boolean isSenior;
  @Column(name = "student")
  private boolean isStudent;
  @Column(name = "u_10")
  private boolean isU10;
  @Column(name = "first_contest")
  private boolean isFirstContest;
  @Column(name = "seminar_member")
  private boolean isSeminarMember;

  @Override
  public String toString() {
    return "PlayerEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", emailAddr='" + emailAddr + '\'' +
        ", age=" + age +
        ", city='" + city + '\'' +
        ", goClub='" + goClub + '\'' +
        ", country='" + country + '\'' +
        ", goRank=" + goRank +
        ", paymentStatus=" + paymentStatus +
        ", registrationFormMessage='" + registrationFormMessage + '\'' +
        ", isNeedsSleepOver=" + isNeedsSleepOver +
        ", isAttendsBreakfast=" + isAttendsBreakfast +
        ", isGoClubMember=" + isGoClubMember +
        ", isDiscounted=" + isDiscounted +
        ", isFemale=" + isFemale +
        ", isSenior=" + isSenior +
        ", isStudent=" + isStudent +
        ", isU10=" + isU10 +
        ", isFirstContest=" + isFirstContest +
        ", isSeminarMember=" + isSeminarMember +
        ", lastModified=" + lastModified +
        '}';
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getGoClub() {
    return goClub;
  }

  public void setGoClub(String goClub) {
    this.goClub = goClub;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public GoRank getGoRank() {
    return goRank;
  }

  public void setGoRank(GoRank goRank) {
    this.goRank = goRank;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getRegistrationFormMessage() {
    return registrationFormMessage;
  }

  public void setRegistrationFormMessage(String registrationFormMessage) {
    this.registrationFormMessage = registrationFormMessage;
  }

  public boolean isNeedsSleepOver() {
    return isNeedsSleepOver;
  }

  public void setNeedsSleepOver(boolean needsSleepOver) {
    isNeedsSleepOver = needsSleepOver;
  }

  public boolean isAttendsBreakfast() {
    return isAttendsBreakfast;
  }

  public void setAttendsBreakfast(boolean attendsBreakfast) {
    isAttendsBreakfast = attendsBreakfast;
  }

  public boolean isGoClubMember() {
    return isGoClubMember;
  }

  public void setGoClubMember(boolean goClubMember) {
    isGoClubMember = goClubMember;
  }

  public boolean isDiscounted() {
    return isDiscounted;
  }

  public void setDiscounted(boolean discounted) {
    isDiscounted = discounted;
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
}
