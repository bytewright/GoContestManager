package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.util.PaymentStatus;

public class PlayerAddPanel extends Panel {

  public PlayerAddPanel(String contentId, Contest contest) {
    super(contentId);
    Player player = new Player();
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    player.setContestIdentifier(contest.getUniqueId());
    Form<Player> playerAddForm = new PlayerForm("playerAdd", Model.of(player));
    Form<Player> playerParseForm = new PlayerParseForm("playerParse", Model.of(player));

    add(playerAddForm);
    add(playerParseForm);
  }

}
