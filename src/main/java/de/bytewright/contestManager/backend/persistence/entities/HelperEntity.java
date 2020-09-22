package de.bytewright.contestManager.backend.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "helpers", schema = "gcm")
public class HelperEntity extends AbstractPersonEntity {

  @NotBlank
  @Size(max = 255)
  private String phoneNumber;
  @Embedded
  @AttributeOverride(name = "name", column = @Column(name = "loc_name"))
  private LocationEmbeddable location;

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
