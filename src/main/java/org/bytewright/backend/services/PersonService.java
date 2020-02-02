package org.bytewright.backend.services;

import javax.annotation.Nullable;

import org.bytewright.backend.persistence.dtos.Player;

public interface PersonService {
  @Nullable
  Player getPlayer(Long playerId);

  Long saveOrUpdatePlayer(Player player);

  void deletePlayer(Player player);
}
