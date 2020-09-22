package de.bytewright.contestManager.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.Player;

public class PlayerEditPanel extends Panel {
  public PlayerEditPanel(String contentId, Contest contest, Player player) {
    super(contentId);

    Form<Player> playerEditForm = new PlayerForm("playerEdit", Model.of(player));
    add(playerEditForm);
  }
}
