package org.bytewright.backend.persistence.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractPersonEntity extends BaseAuditedEntity {

  @Size(min = 1, max = 100)
  @Column(name = "email")
  protected String emailAddr;
  @NotBlank
  @Size(max = 100)
  @Column(name = "first_name")
  protected String name;
  @NotBlank
  @Size(max = 100)
  @Column(name = "last_name")
  protected String surname;

  @ManyToOne(optional = false)
  @JoinColumn(name = "contest_id", nullable = false)
  protected ContestEntity contestEntity;

  public ContestEntity getContestEntity() {
    return contestEntity;
  }

  public void setContestEntity(ContestEntity contestEntity) {
    this.contestEntity = contestEntity;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
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
}
