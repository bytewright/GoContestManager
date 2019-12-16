package org.bytewright.backend.dto;

import java.io.Serializable;

public class Contest implements Serializable {
  private String identifier;
  private String name;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
