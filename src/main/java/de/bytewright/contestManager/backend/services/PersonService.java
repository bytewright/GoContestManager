package de.bytewright.contestManager.backend.services;

import javax.annotation.Nullable;

import de.bytewright.contestManager.backend.persistence.dtos.Player;

public interface PersonService {
  @Nullable
  Player getPlayer(Long playerId);

  Long saveOrUpdatePlayer(Player player);

  void deletePlayer(Player player);
}
