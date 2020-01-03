package org.bytewright.frontend.components.manage.players;

import java.util.List;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerAddForm extends Form<String> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAddForm.class);
  private final TextField<String> name;
  private final TextField<String> surname;
  private final EmailTextField emailAddr;
  private final TextField<String> club;
  private final NumberTextField<Integer> age;
  private final DropDownChoice<GoRank> rank;
  private final CheckBox isFemale;
  private final CheckBox isStudent;
  private final CheckBox isSenior;
  private final CheckBox isU10;
  private final Contest contest;

  @SpringBean
  private PersonService personService;

  public PlayerAddForm(String playerAdd, Contest contest) {
    super(playerAdd);
    this.contest = contest;
    name = new TextField<>("name", Model.of(""), String.class);
    surname = new TextField<>("surname", Model.of(""), String.class);
    emailAddr = new EmailTextField("emailAddr", Model.of(""));
    club = new TextField<>("club", Model.of(""), String.class);
    rank = new DropDownChoice<>("rank", Model.of(GoRank.DAN_01), List.of(GoRank.values()));
    age = new NumberTextField<>("age", Model.of(10), Integer.class);

    isFemale = new CheckBox("isFemale", Model.of(false));
    isStudent = new CheckBox("isStudent", Model.of(false));
    isSenior = new CheckBox("isSenior", Model.of(false));
    isU10 = new CheckBox("isU10", Model.of(false));

    add(name);
    add(surname);
    add(emailAddr);
    add(club);
    add(rank);
    add(age);
    add(isFemale);
    add(isStudent);
    add(isSenior);
    add(isU10);
  }

  @Override
  protected void onSubmit() {
    Player player = new Player();
    player.setName(name.getModelObject());
    player.setSurname(surname.getModelObject());
    player.setGoClub(club.getModelObject());
    player.setAge(age.getModelObject());
    player.setEmailAddr(emailAddr.getModelObject());
    player.setGoRank(rank.getModelObject());
    player.setFemale(isFemale.getModelObject());
    player.setSenior(isSenior.getModelObject());
    player.setStudent(isStudent.getModelObject());
    player.setU10(isU10.getModelObject());
    player.setPaymentStatus(PaymentStatus.NOT_PAID);
    LOGGER.info("{} submitted! {}", this.getClass(), player);
    personService.addPlayer(contest, player);
  }
}
