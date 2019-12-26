package org.bytewright.backend.dto;

import java.io.Serializable;

public class Location implements Serializable {
  private String street;
  private String streetNum;
  private String city;
  private String name;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNum() {
    return streetNum;
  }

  public void setStreetNum(String streetNum) {
    this.streetNum = streetNum;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
