package org.bytewright.backend.services;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.util.PersonUtil;
import org.bytewright.backend.util.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
  @Autowired
  private ContestService contestService;

  public Player getPlayer(Long playerId) {
    // todo db lookup
    return contestService.getValidContests().stream()
        .flatMap(contest -> contest.getPlayers().stream())
        .filter(player -> player.getUniqueId().equals(playerId))
        .findFirst().orElseThrow();
  }

  public Long saveOrUpdatePlayer(Contest contest, Player player) {
    LOGGER.info("Adding/Updating Player {} to contest {}", player, contest.getUniqueId());
    player.setUniqueId(PersonUtil.nextId.getAndIncrement());
    contest.getPlayers().add(player);
    return player.getUniqueId();
  }

  public Long saveOrUpdatePlayer(Player player) {
    return saveOrUpdatePlayer(SessionInfo.getSSelectedContest(), player);
  }

  public void deletePlayer(Player player) {
    Contest contest = SessionInfo.getSSelectedContest();
    LOGGER.info("Deleting Player {} from contest {}", player, contest);
    contest.getPlayers().remove(player);
  }
}
