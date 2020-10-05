package de.bytewright.contestManager.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.util.PaymentStatus;
import de.bytewright.contestManager.backend.util.PlayerFromTextParser;
import de.bytewright.contestManager.backend.util.exceptions.PlayerParseException;

/**
 * Specific for mail format used by kreuzschnitt
 */
@Service
public class PlayerImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerImporter.class);
  private final PlayerFromTextParser parser;

  @Autowired
  public PlayerImporter(PlayerFromTextParser parser) {
    this.parser = parser;
  }

  /**
   * @throws PlayerParseException if any required field could not be parsed
   */
  public Player parse(String textToParse) throws PlayerParseException {
    Player player = new Player();
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    player = parser.setMailAndName(player, textToParse);
    player = parser.setRegistrationFormMessage(player, textToParse);
    player = parser.setGoRank(player, textToParse);
    player = parser.setClubAndCity(player, textToParse);
    player = parser.parseAgeGroup(player, textToParse);
    player = parser.setDiscountFlags(player, textToParse);
    LOGGER.info("Parsed player from text: {}", player);
    return player;
  }

}
