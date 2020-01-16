package org.bytewright.backend.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Contest implements Serializable {
  private ContestSettings contestSettings;
  private Set<Helper> helpers;
  private Set<Organiser> organisers;
  private Set<Player> players;
  private String uId;

  public Contest() {
    uId = "NULL";
    contestSettings = new ContestSettings();
    helpers = new HashSet<>();
    organisers = new HashSet<>();
    players = new HashSet<>();
  }

  public ContestSettings getContestSettings() {
    return contestSettings;
  }

  public void setContestSettings(ContestSettings contestSettings) {
    this.contestSettings = contestSettings;
  }

  public Set<Helper> getHelpers() {
    return helpers;
  }

  public void setHelpers(Set<Helper> helpers) {
    this.helpers = helpers;
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

  public String getUniqueId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  @Override
  public String toString() {
    return "Contest{" +
        "contestSettings=" + contestSettings +
        ", helpers=" + helpers +
        ", organisers=" + organisers +
        ", players=" + players +
        ", uId='" + uId + '\'' +
        '}';
  }
}
