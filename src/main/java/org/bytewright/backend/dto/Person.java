package org.bytewright.backend.dto;

import java.io.Serializable;

public class Person implements Serializable {
  private String name;
  private String emailAddr;
  private Location adress;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location getAdress() {
    return adress;
  }

  public void setAdress(Location adress) {
    this.adress = adress;
  }
}
