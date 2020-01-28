package org.bytewright.backend.services;

import javax.annotation.Nullable;

import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.persistence.entities.PlayerEntity;
import org.bytewright.backend.persistence.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Nullable
  public Player getPlayer(Long playerId) {
    return playerRepository.findById(playerId)
        .map(entity -> modelMapper.map(entity, Player.class))
        .orElse(null);
  }

  public Long saveOrUpdatePlayer(Player player) {
    PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
    LOGGER.info("Adding/Updating Player {} to contest {}", player, playerEntity.getContestEntity().getShortIdentifier());
    return playerRepository.save(playerEntity).getId();
  }

  public void deletePlayer(Player player) {
    PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
    LOGGER.info("Deleting Player {} from contest {}", player, playerEntity.getContestEntity().getShortIdentifier());
    playerRepository.delete(playerEntity);
  }
}
