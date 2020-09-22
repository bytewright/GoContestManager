package de.bytewright.contestManager.backend.webAdmin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.bytewright.contestManager.backend.contestSpecific.KreuzschnittMailFetcher;
import de.bytewright.contestManager.backend.persistence.dtos.Player;

@Controller
public class Test {
  @Autowired
  private KreuzschnittMailFetcher mailFetcher;

  @GetMapping(value = "/admin/loadKreuzschnitt", produces = "application/json")
  @ResponseBody
  public Map<String, Object> jsonController() {
    List<Player> players = mailFetcher.processMails();
    return Map.of("players", players);
  }
}
