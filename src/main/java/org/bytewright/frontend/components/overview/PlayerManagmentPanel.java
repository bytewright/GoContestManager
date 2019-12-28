package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;

import java.util.List;
import java.util.Set;

public class PlayerManagmentPanel extends Panel {

  @SpringBean
  private PersonService contestService;

  public PlayerManagmentPanel(String contentId, Contest contest) {
    super(contentId);
    add(new PlayerListView("players", contest.getPlayers()));
  }

  private class PlayerListView extends ListView<Player> {

    public PlayerListView(String id, Set<Player> players) {
      super(id, List.copyOf(players));
    }

    @Override
    protected void populateItem(ListItem<Player> item) {
      IModel<Player> model = item.getModel();
      item.add(new Label("name", new PropertyModel<>(model, "name")));
      item.add(new Label("goClub", new PropertyModel<>(model, "goClub")));
      item.add(new Label("rank", new PropertyModel<>(model, "goRank")));
      item.add(new Label("paymentStatus", new PropertyModel<>(model, "paymentStatus")));
      item.add(new Label("isStudent", new PropertyModel<>(model, "isStudent")));
      item.add(new Label("isSenior", new PropertyModel<>(model, "isSenior")));
      item.add(new Label("isFemale", new PropertyModel<>(model, "isFemale")));
      item.add(new Label("isU10", new PropertyModel<>(model, "isU10")));
    }
  }
}