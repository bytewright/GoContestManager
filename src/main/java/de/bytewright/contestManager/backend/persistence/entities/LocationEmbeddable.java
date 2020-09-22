package de.bytewright.contestManager.backend.persistence.entities;

import javax.persistence.Embeddable;

@Embeddable
public class LocationEmbeddable {
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

  @Override
  public String toString() {
    return "LocationEmbeddable{" +
        "street='" + street + '\'' +
        ", streetNum='" + streetNum + '\'' +
        ", city='" + city + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
