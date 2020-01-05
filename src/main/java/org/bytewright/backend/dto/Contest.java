package org.bytewright.backend.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

public class Contest implements Serializable {
  private ContestSettings contestSettings;
  private ZonedDateTime dateEnd;
  private ZonedDateTime dateStart;
  private Set<Helper> helpers;
  private Location location;
  private String name;
  private Set<Organiser> organisers;
  private Set<Player> players;
  private String uId;

  public Contest() {
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public ZonedDateTime getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(ZonedDateTime dateEnd) {
    this.dateEnd = dateEnd;
  }

  public ContestSettings getContestSettings() {
    return contestSettings;
  }

  public void setContestSettings(ContestSettings contestSettings) {
    this.contestSettings = contestSettings;
  }

  public Set<Organiser> getOrganisers() {
    return organisers;
  }

  public void setOrganisers(Set<Organiser> organisers) {
    this.organisers = organisers;
  }

  public Set<Player> getPlayers() {
    return players;
  }

  public void setPlayers(Set<Player> players) {
    this.players = players;
  }

  public Set<Helper> getHelpers() {
    return helpers;
  }

  public void setHelpers(Set<Helper> helpers) {
    this.helpers = helpers;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ZonedDateTime getDateStart() {
    return dateStart;
  }

  public void setDateStart(ZonedDateTime dateStart) {
    this.dateStart = dateStart;
  }
}
