package org.bytewright.backend.util;

import org.apache.commons.lang3.StringUtils;
import org.bytewright.backend.dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerImporter.class);

  public Player parse(String textToParse) {
    //TODO
    LOGGER.info("Parsing player from text: {}", StringUtils.abbreviate(textToParse, 50));
    Player player = new Player();
    player.setName("PARSED!");
    return player;
  }
}
