package org.bytewright.frontend.components.manage.players;

import java.util.List;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerAddForm extends Form<Player> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAddForm.class);
  private final Contest contest;
  private final Player player;

  @SpringBean
  private PersonService personService;

  public PlayerAddForm(String playerAdd, Contest contest) {
    super(playerAdd, Model.of(new Player()));
    this.contest = contest;
    this.player = getModelObject();
    add(new TextField<>("name", LambdaModel.of(player::getName, player::setName), String.class));
    add(new TextField<>("surname", LambdaModel.of(player::getSurname, player::setSurname), String.class));
    add(new EmailTextField("emailAddr", LambdaModel.of(player::getEmailAddr, player::setEmailAddr)));
    add(new TextField<>("club", LambdaModel.of(player::getGoClub, player::setGoClub), String.class));
    add(new DropDownChoice<>("rank", LambdaModel.of(player::getGoRank, player::setGoRank), List.of(GoRank.values())));
    add(new NumberTextField<>("age", LambdaModel.of(player::getAge, player::setAge), Integer.class));

    add(new CheckBox("isFemale", LambdaModel.of(player::isFemale, player::setFemale)));
    add(new CheckBox("isStudent", LambdaModel.of(player::isStudent, player::setStudent)));
    add(new CheckBox("isSenior", LambdaModel.of(player::isSenior, player::setSenior)));
    add(new CheckBox("isU10", LambdaModel.of(player::isU10, player::setU10)));
  }

  @Override
  protected void onSubmit() {
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    LOGGER.info("{} submitted! {}", this.getClass(), player);
    personService.addPlayer(contest, player);
  }
}
