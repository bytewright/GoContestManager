package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;
import org.bytewright.frontend.pages.PlayerManagementPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlayerAddPanel extends Panel {

  @SpringBean
  private PersonService personService;

  public PlayerAddPanel(String contentId, Contest contest) {
    super(contentId);
    Player player = new Player();
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    Form<Player> playerAddForm = new PlayerAddForm("playerAdd", Model.of(player), contest);
    playerAddForm.add(new PlayerValidator());
    Form<Player> playerParseForm = new PlayerParseForm("playerParse", Model.of(player), contest);

    add(playerAddForm);
    add(playerParseForm);
  }

  private static class PlayerValidator extends Behavior implements INullAcceptingValidator<Player> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerValidator.class);

    @Override
    public void validate(IValidatable<Player> validatable) {
      LOGGER.info("validating {}", validatable.getValue());
    }
  }
}
