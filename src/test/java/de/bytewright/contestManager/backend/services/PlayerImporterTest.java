package de.bytewright.contestManager.backend.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bytewright.contestManager.backend.contestSpecific.KreuzschnittPlayerFromTextParser;
import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.util.PlayerFromTextParser;

class PlayerImporterTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerImporterTest.class);

  @Ignore
  @Test
  void testImportMails() throws IOException {
    // GIVEN
    PlayerFromTextParser parser = new KreuzschnittPlayerFromTextParser();
    PlayerImporter testee = new PlayerImporter(parser);
    String[] mails = Files.readString(Path.of("Mails.txt")).split("\\|\\|\\|");

    for (String mail : mails) {
      // WHEN
      Player player = testee.parse(mail);
      // THEN
      LOGGER.info("Parsed Player: {}", player);
      Assertions.assertNotNull(player.getName());
      Assertions.assertNotNull(player.getSurname());
      Assertions.assertNotNull(player.getEmailAddr());
      Assertions.assertNotNull(player.getCity());
      Assertions.assertNotNull(player.getCountry());
      Assertions.assertNotNull(player.getGoClub());
      if (player.getGoRank() == null) {
        LOGGER.warn("Failed to parse go rank from mail: {}", mail);
      } else {
        Assertions.assertNotNull(player.getGoRank());
      }
      Assertions.assertNotNull(player.getRegistrationFormMessage());
      Assertions.assertNull(player.getContestIdentifier());
      Assertions.assertTrue(player.getAge() >= 0 && player.getAge() <= 200, "unexpected age: " + player.getAge());
    }
  }
}