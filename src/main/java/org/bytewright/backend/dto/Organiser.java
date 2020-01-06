package org.bytewright.backend.dto;

public class Organiser extends Person {
  private String phoneNumber;
  private Location address;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Location getAddress() {
    return address;
  }

  public void setAddress(Location address) {
    this.address = address;
  }
}
