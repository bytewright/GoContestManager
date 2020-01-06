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
  @Autowired
  private UserService userService;

  public Set<Player> getPlayers(Contest contest) {
    return Set.of();
  }

  public void addPlayer(Contest contest, Player player) {
    LOGGER.info("Adding Player {} to contest {}", player, contest.getUniqueId());
  }

  public Player getPlayer(Long playerId) {
    // todo db lookup
    return contestService.getValidContests().stream()
        .flatMap(contest -> contest.getPlayers().stream())
        .filter(player -> player.getUniqueId().equals(playerId))
        .findFirst().orElseThrow();
  }

  public Long saveOrUpdatePlayer(Contest contest, Player player) {
    LOGGER.info("Adding/Updating Player {} to contest {}", player, contest.getUniqueId());
    return player.getUniqueId();
  }

  public Long saveOrUpdatePlayer(Player player) {
    return saveOrUpdatePlayer(userService.getSessionInfo().getSelectedContest(), player);
  }

  public void deletePlayer(Player player) {
    Contest contest = userService.getSessionInfo().getSelectedContest();
    LOGGER.info("Deleting Player {} from contest {}", player, contest);
  }
}
