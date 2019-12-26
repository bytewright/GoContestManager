package org.bytewright.backend.services;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PersonService {
  public Set<Player> getPlayers(Contest contest) {
    return Set.of();
  }
}
