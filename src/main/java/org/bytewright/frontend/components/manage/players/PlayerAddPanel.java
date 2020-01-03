package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.bytewright.backend.dto.Contest;

public class PlayerAddPanel extends Panel {
  public PlayerAddPanel(String contentId, Contest contest) {
    super(contentId);
    Form<String> playerAddForm = new PlayerAddForm("playerAdd", contest);
    Form<String> playerParseForm = new PlayerParseForm("playerParse", contest);
    add(playerAddForm);
    add(playerParseForm);
  }
}
