package org.bytewright.backend.services;

import java.util.Set;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
  @Autowired
  private ContestService contestService;

  public Set<Player> getPlayers(Contest contest) {
    return Set.of();
  }

  public void addPlayer(Contest contest, Player player) {
    LOGGER.info("Adding Player {} to contest {}", player, contest.getuId());
  }

  public Player getPlayer(Long playerId) {
    // todo db lookup
    return contestService.getValidContests().stream()
        .flatMap(contest -> contest.getPlayers().stream())
        .filter(player -> player.getUniqueId().equals(playerId))
        .findFirst().orElseThrow();
  }

  public void updatePlayer(Player modelObject) {
    LOGGER.info("\nold: {}\nnew {}", getPlayer(modelObject.getUniqueId()), modelObject);
  }
}
