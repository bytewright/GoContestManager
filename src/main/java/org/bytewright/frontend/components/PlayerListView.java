package org.bytewright.frontend.components;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.PaymentStatus;
import org.bytewright.frontend.pages.PlayerEditPage;
import org.bytewright.frontend.pages.PlayerManagementPage;

public class PlayerListView extends ListView<Player> {
  @SpringBean
  private PersonService personService;

  public PlayerListView(String id, List<Player> players) {
    super(id, players);
  }

  @Override
  protected void populateItem(ListItem<Player> item) {
    Player player = item.getModel().getObject();
    item.add(new Label("name", LambdaModel.of(player::getName, player::setName)));
    item.add(new Label("surname", LambdaModel.of(player::getSurname, player::setSurname)));
    item.add(new Label("goClub", LambdaModel.of(player::getGoClub, player::setGoClub)));
    item.add(new Label("rank", LambdaModel.of(() -> player.getGoRank().getAbbreviation())));
    item.add(new Label("paymentStatus", LambdaModel.of(player::getPaymentStatus, player::setPaymentStatus)));
    CheckBox student = new CheckBox("isDiscount", LambdaModel.of(player::isDiscounted));
    student.setEnabled(false);
    item.add(student);
    CheckBox senior = new CheckBox("isSeminar", LambdaModel.of(player::isSeminarMember));
    senior.setEnabled(false);
    item.add(senior);
    CheckBox female = new CheckBox("isClub", LambdaModel.of(player::isGoClubMember));
    female.setEnabled(false);
    item.add(female);
    CheckBox u10 = new CheckBox("isBreakfast", LambdaModel.of(player::isAttendsBreakfast));
    u10.setEnabled(false);
    item.add(u10);
    Form<String> playerEditForm = new Form<>("playerEditForm") {
      @Override
      protected void onSubmit() {
        PageParameters pageParameters = new PageParameters();
        pageParameters.add(PlayerEditPage.PLAYER_PARAM, player.getUniqueId());
        setResponsePage(PlayerEditPage.class, pageParameters);
      }
    };
    item.add(playerEditForm);
    Form<String> playerPaidForm = new Form<>("playerPaidForm") {
      @Override
      protected void onSubmit() {
        player.setPaymentStatus(PaymentStatus.FULLY_PAID);
        personService.saveOrUpdatePlayer(player);
      }
    };
    item.add(playerPaidForm);
    Form<String> playerDeleteForm = new Form<>("playerDeleteForm") {
      @Override
      protected void onSubmit() {
        personService.deletePlayer(player);
        setResponsePage(PlayerManagementPage.class);
      }
    };
    item.add(playerDeleteForm);
  }
}
