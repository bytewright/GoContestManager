package org.bytewright.backend.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bytewright.backend.dto.Player;

public class PlayerExporter {
  private Set<Player> players;

  public PlayerExporter(Set<Player> players) {
    this.players = players;
  }

  public List<String> getLines() {
    /*TODO McMahon export*/
    return players.stream()
        .map(Player::toString)
        .collect(Collectors.toList());
  }
}
