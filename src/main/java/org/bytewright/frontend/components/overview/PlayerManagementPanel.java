package org.bytewright.frontend.components.overview;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.util.PlayerExporter;
import org.bytewright.frontend.pages.PlayerAddPage;
import org.bytewright.frontend.pages.PlayerEditPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.lambda.components.ComponentFactory;

public class PlayerManagementPanel extends Panel {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerManagementPanel.class);

  public PlayerManagementPanel(String contentId, Contest contest) {
    super(contentId);
    Set<Player> players = contest.getPlayers();
    add(ComponentFactory.link("addPlayerLink", components -> setResponsePage(PlayerAddPage.class)));
    add(new DownloadLink("exportPlayerLink", LambdaModel.of(() -> makeFile(contest, players))));
    add(new PlayerListView("players", players));
  }

  private File makeFile(Contest contest, Set<Player> players) {
    try {
      PlayerExporter playerExporter = new PlayerExporter(players);
      Path tmpFile = Files.createTempFile(contest.getuId() + "_player_export", ".csv");
      Files.write(tmpFile, playerExporter.getLines());
      LOGGER.info("Created Export at: {}", tmpFile.toAbsolutePath().toString());
      return tmpFile.toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static class PlayerListView extends ListView<Player> {

    public PlayerListView(String id, Set<Player> players) {
      super(id, List.copyOf(players));
    }

    @Override
    protected void populateItem(ListItem<Player> item) {
      Player player = item.getModel().getObject();
      item.add(new Label("name", LambdaModel.of(player::getName, player::setName)));
      item.add(new Label("goClub", LambdaModel.of(player::getGoClub, player::setGoClub)));
      item.add(new Label("rank", LambdaModel.of(() -> player.getGoRank().getAbbreviation())));
      item.add(new Label("paymentStatus", LambdaModel.of(player::getPaymentStatus, player::setPaymentStatus)));
      CheckBox student = new CheckBox("isStudent", LambdaModel.of(player::isStudent));
      student.setEnabled(false);
      item.add(student);
      CheckBox senior = new CheckBox("isSenior", LambdaModel.of(player::isSenior));
      senior.setEnabled(false);
      item.add(senior);
      CheckBox female = new CheckBox("isFemale", LambdaModel.of(player::isFemale));
      female.setEnabled(false);
      item.add(female);
      CheckBox u10 = new CheckBox("isU10", LambdaModel.of(player::isU10));
      u10.setEnabled(false);
      item.add(u10);
      Form<String> playerEditForm = new Form<>("playerEditForm") {
        @Override
        protected void onSubmit() {
          PageParameters pageParameters = new PageParameters();
          pageParameters.add("playerId", player.getUniqueId());
          //          new PlayerEditPage(pageParameters)
          setResponsePage(PlayerEditPage.class, pageParameters);
        }
      };
      item.add(playerEditForm);
    }
  }
}
