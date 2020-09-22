package de.bytewright.contestManager.backend.contestSpecific;

import java.util.List;

import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Player;

@Component
public class KreuzschnittMailFetcher {
  public List<Player> processMails() {
    // connect to imap
    // list all amils
    // filter registrations
    // convert to players for contest
    return List.of();
  }
}
