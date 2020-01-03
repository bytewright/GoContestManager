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
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.frontend.pages.PlayerManagementPage;

public class PlayerEditForm extends Form<Player> {

  @SpringBean
  private PersonService personService;
  
  public PlayerEditForm(String id, Player model) {
    super(id, Model.of(model));
    add(new TextField<>("name", LambdaModel.of(model::getName, model::setName), String.class));
    add(new TextField<>("surname", LambdaModel.of(model::getSurname, model::setSurname), String.class));
    add(new EmailTextField("emailAddr", LambdaModel.of(model::getEmailAddr, model::setEmailAddr)));
    add(new TextField<>("club", LambdaModel.of(model::getGoClub, model::setGoClub), String.class));
    add(new DropDownChoice<>("rank", LambdaModel.of(model::getGoRank, model::setGoRank), List.of(GoRank.values())));
    add(new NumberTextField<>("age", LambdaModel.of(model::getAge, model::setAge), Integer.class));

    add(new CheckBox("isFemale", LambdaModel.of(model::isFemale, model::setFemale)));
    add(new CheckBox("isStudent", LambdaModel.of(model::isStudent, model::setStudent)));
    add(new CheckBox("isSenior", LambdaModel.of(model::isSenior, model::setSenior)));
    add(new CheckBox("isU10", LambdaModel.of(model::isU10, model::setU10)));
  }

  @Override
  protected void onSubmit() {
    personService.updatePlayer(getModelObject());
    setResponsePage(PlayerManagementPage.class);
  }
}
