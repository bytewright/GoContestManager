package org.bytewright.backend.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.util.exceptions.PlayerParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerImporter.class);

  /**
   * @throws PlayerParseException if any required field could not be parsed
   */
  public Player parse(String textToParse) throws PlayerParseException {
    Player player = new Player();
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    parseNameMailLine(player, textToParse);
    parseStrength(player, textToParse);
    parseClub(player, textToParse);
    parseAgeGroup(player, textToParse);
    parseFlags(player, textToParse);
    parsePlayerMessage(player, textToParse);
    LOGGER.info("Parsed player from text: {}", player);
    return player;
  }

  private void parsePlayerMessage(Player player, String textToParse) {
    String playerMsg = textToParse.lines()
        .dropWhile(s -> !s.equalsIgnoreCase("Nachrichtentext:".toLowerCase()))
        .collect(Collectors.joining("\n"));
    LOGGER.debug("parsed message for player {}:{}", player.getSurname(), playerMsg);
    player.setRegistrationFormMessage(playerMsg);
  }

  private void parseFlags(Player player, String textToParse) {
    // todo make configureable
    Map<String, Consumer<Boolean>> flagSetter = new HashMap<>();
    flagSetter.put("Go-Seminar Teilnahme am Freitag", player::setSeminarMember);
    //flagSetter.put("private Übernachtung FR-SA gewünscht", player::setSeminarMember);
    //flagSetter.put("private Übernachtung SA-SO gewünscht", player::setSeminarMember);
    flagSetter.put("Frühstück Sonntag (3€)", player::setAttendsBreakfast);
    flagSetter.put("Mitglied im Go Verband", player::setGoClubMember);
    flagSetter.put("Ermäßigung (Schüler/Azubi/Student/Rentner/arbeitslos)", player::setDiscounted);
    flagSetter.put("Student", player::setStudent);
    flagSetter.put("Damen", player::setFemale);
    flagSetter.put("Erstes Turnier", player::setFirstContest);

    for (Map.Entry<String, Consumer<Boolean>> entry : flagSetter.entrySet()) {
      Optional<String> optional = textToParse.lines()
          .filter(s -> s.equalsIgnoreCase(entry.getKey().toLowerCase()))
          .peek(s -> LOGGER.debug("Found string {} in textToParse", s))
          .findFirst();
      entry.getValue().accept(optional.isPresent());
    }
  }

  private void parseAgeGroup(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Alter: ([0-9+-]*)");
    Matcher matcher = pattern.matcher(textToParse);
    if (matcher.find()) {
      String group = matcher.group(1);
      // TODO Agegroups
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
      player.setCity(matcher.group(1));
    }
  }

  private void parseStrength(Player player, String textToParse) {
    Pattern pattern = Pattern.compile("Spielstärke (\\d\\d|\\d) *(\\S+)");
    Matcher matcher = pattern.matcher(textToParse);
    if (!matcher.find()) {
      throw new PlayerParseException("Failed to parse Rank from " + textToParse);
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
    if (nameParts.length > 1) {
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
