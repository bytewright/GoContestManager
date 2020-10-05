package de.bytewright.contestManager.backend.persistence.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class Contest implements Serializable {
  private ContestSettings contestSettings;
  private Set<Helper> helpers;
  private Set<Organiser> organisers;
  private Set<Player> players;
  private String uniqueID;
  private Map<String, String> extraData;

  public Contest() {
    uniqueID = "NULL";
    contestSettings = new ContestSettings();
    helpers = new HashSet<>();
    organisers = new HashSet<>();
    players = new HashSet<>();
    extraData = new HashMap<>();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getHelpers(), getOrganisers(), getPlayers(), uniqueID);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Contest contest = (Contest) o;
    return uniqueID.equals(contest.uniqueID);
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
    return uniqueID;
  }

  public void setUniqueID(String uniqueID) {
    this.uniqueID = uniqueID;
  }

  public Map<String, String> getExtraData() {
    return extraData;
  }

  public void setExtraData(Map<String, String> extraData) {
    this.extraData = extraData;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Contest.class.getSimpleName() + "[", "]")
        .add("contestSettings=" + contestSettings)
        .add("helpers=" + helpers)
        .add("organisers=" + organisers)
        .add("players=" + players)
        .add("uniqueID='" + uniqueID + "'")
        .add("extradata=" + extraData)
        .toString();
  }
}
