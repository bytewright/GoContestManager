package org.bytewright.backend.util;

import org.bytewright.backend.dto.Player;
import org.bytewright.backend.util.exceptions.PlayerParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerImporter.class);

  public Player parse(String textToParse) {
    Player player = new Player();
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    parseNameMailLine(player, textToParse);
    parseStrength(player, textToParse);
    parseClub(player, textToParse);
    parseAgeGroup(player, textToParse);
    parseFlags(player, textToParse);
    LOGGER.info("Parsed player from text: {}", player);
    return player;
  }

  private void parseFlags(Player player, String textToParse) {
    /* TODO
[checkbox seminar "Go-Seminar Teilnahme am Freitag"]
[checkbox fr-sa "private Übernachtung FR-SA gewünscht"]
[checkbox sa-so "private Übernachtung SA-SO gewünscht"]
[checkbox breakfast "Frühstück Sonntag (3€)"]
[checkbox lv-member "Mitglied im Go Verband"]
[checkbox reduced "Ermäßigung (Schüler/Azubi/Student/Rentner/arbeitslos)"]
[checkbox student "Student"]
[checkbox woman "Damen"]
[checkbox first "Erstes Turnier"]
    */
  }

  private void parseAgeGroup(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Alter: ([0-9+-]*)");
    Matcher matcher = pattern.matcher(textToParse);
    if (matcher.find()) {
      String group = matcher.group(1);
      if ("55+".equals(group)) {
        player.setAge(55);
        player.setSenior(true);
      } else if ("0-10".equals(group)) {
        player.setAge(0);
        player.setU10(true);
      } else if ("11-17".equals(group)) {
        player.setAge(11);
      } else if ("18-54".equals(group)) {
        player.setAge(18);
      } else {
        throw new PlayerParseException("Unexpected value: " + group);
      }
    }
  }

  private void parseClub(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Aus: (\\S+| )*");
    Matcher matcher = pattern.matcher(textToParse);
    if (matcher.find()) {
      player.setGoClub(matcher.group(1));
    }
  }

  private void parseStrength(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Spielstärke (\\d) (\\S+)");
    Matcher matcher = pattern.matcher(textToParse);
    if (!matcher.find()) {
      throw new PlayerParseException("Failed to parse name from " + textToParse);
    }
    Optional<GoRankName> rankName = GoRankName.from(matcher.group(2));
    Optional<GoRank> goRank = GoRank.from(Integer.valueOf(matcher.group(1)), rankName.orElse(GoRankName.KYU));
    player.setGoRank(goRank.orElse(null));
  }

  private void parseNameMailLine(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Von: (.*) <(.*)>");
    Matcher matcher = pattern.matcher(textToParse);
    if (!matcher.find()) {
      throw new PlayerParseException("Failed to parse name from " + textToParse);
    }
    String nameCandidate = matcher.group(1);
    String mailCandidate = matcher.group(2);
    String[] nameParts = nameCandidate.split(" ");
    if (nameParts.length > 2) {
      String surName = nameParts[nameParts.length - 1];
      player.setSurname(surName);
      player.setName(nameCandidate.replace(" " + surName, ""));
    } else {
      player.setSurname(nameCandidate);
      player.setName("");
    }
    player.setEmailAddr(mailCandidate);
  }
}
