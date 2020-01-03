package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;

public class PlayerEditPanel extends Panel {
  public PlayerEditPanel(String contentId, Contest contest, Player player) {
    super(contentId);
    Form<Player> playerEditForm = new PlayerEditForm("playerEdit", player);
    add(playerEditForm);
  }
}
