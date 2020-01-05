package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;

public class PlayerAddPanel extends Panel {
  public PlayerAddPanel(String contentId, Contest contest) {
    super(contentId);
    Form<Player> playerAddForm = new PlayerAddForm("playerAdd", contest);
    Form<Player> playerParseForm = new PlayerParseForm("playerParse", contest);
    add(playerAddForm);
    add(playerParseForm);
  }
}
