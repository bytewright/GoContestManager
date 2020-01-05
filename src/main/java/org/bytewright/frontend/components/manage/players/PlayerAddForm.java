package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlayerAddForm extends Form<Player> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAddForm.class);
  private final Contest contest;
  private final Player player;

  @SpringBean
  private PersonService personService;

  public PlayerAddForm(String contentId, IModel<Player> model, Contest contest) {
    super(contentId, model);
    this.contest = contest;
    this.player = model.getObject();
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
    LOGGER.info("{} submitted! {}", this.getClass(), player);
    personService.addPlayer(contest, player);
  }
}
