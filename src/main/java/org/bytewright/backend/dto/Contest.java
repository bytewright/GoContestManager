package org.bytewright.backend.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public class Contest implements Serializable {
  private String identifier;
  private String name;
  private Location location;
  private Instant startDate;
  private Instant endDate;
  private ParticipationSettings participationSettings;
  private Set<Organiser> organisers;
  private Set<Player> players;
  private Set<Helper> helpers;

  public Contest() {
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Instant getEndDate() {
    return endDate;
  }

  public void setEndDate(Instant endDate) {
    this.endDate = endDate;
  }

  public ParticipationSettings getParticipationSettings() {
    return participationSettings;
  }

  public void setParticipationSettings(ParticipationSettings participationSettings) {
    this.participationSettings = participationSettings;
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

  public Instant getStartDate() {
    return startDate;
  }

  public void setStartDate(Instant startDate) {
    this.startDate = startDate;
  }
}
