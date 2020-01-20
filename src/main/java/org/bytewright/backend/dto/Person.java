package org.bytewright.backend.dto;

import java.io.Serializable;

public class Person implements Serializable {
  private Long uniqueId;
  private String emailAddr;
  private String name;
  private String surname;
  private String contestIdentifier;

  public String getContestIdentifier() {
    return contestIdentifier;
  }

  public void setContestIdentifier(String contestIdentifier) {
    this.contestIdentifier = contestIdentifier;
  }

  public Long getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(Long uniqueId) {
    this.uniqueId = uniqueId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  @Override
  public String toString() {
    return "Person{" +
        "uniqueId=" + uniqueId +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", emailAddr='" + emailAddr + '\'' +
        '}';
  }
}
