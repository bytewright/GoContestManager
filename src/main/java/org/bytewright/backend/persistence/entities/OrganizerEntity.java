package org.bytewright.backend.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "organizers", schema = "gcm")
public class OrganizerEntity extends AbstractPersonEntity {

  @NotBlank
  @Size(max = 255)
  private String phoneNumber;
  @Embedded
  @AttributeOverride(name = "name", column = @Column(name = "loc_name"))
  private LocationEmbeddable location;

  @Override
  public String toString() {
    return "OrganizerEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", emailAddr='" + emailAddr + '\'' +
        ", location=" + location +
        ", lastModified=" + lastModified +
        '}';
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocationEmbeddable getLocation() {
    return location;
  }

  public void setLocation(LocationEmbeddable location) {
    this.location = location;
  }
}
