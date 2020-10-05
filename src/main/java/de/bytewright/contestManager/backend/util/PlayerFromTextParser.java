package de.bytewright.contestManager.backend.util;

import de.bytewright.contestManager.backend.persistence.dtos.Player;

public interface PlayerFromTextParser {
  Player setRegistrationFormMessage(Player player, String textToParse);

  Player setDiscountFlags(Player player, String textToParse);

  Player parseAgeGroup(Player player, String textToParse);

  Player setClubAndCity(Player player, String textToParse);

  Player setGoRank(Player player, String textToParse);

  Player setMailAndName(Player player, String textToParse);
}
