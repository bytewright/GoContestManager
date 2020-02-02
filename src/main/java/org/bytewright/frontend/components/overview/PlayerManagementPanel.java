package org.bytewright.frontend.components.overview;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.EarningsOverview;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.util.PlayerExporter;
import org.bytewright.frontend.components.PlayerListView;
import org.bytewright.frontend.pages.PlayerAddPage;
import org.bytewright.frontend.pages.PlayerManagementPage;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.lambda.components.ComponentFactory;

// TODO https://www.w3schools.com/howto/howto_js_sticky_header.asp
public class PlayerManagementPanel extends Panel {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerManagementPanel.class);
  @SpringBean
  private ContestService contestService;

  public PlayerManagementPanel(String contentId, Contest contest,
      Pair<String, Predicate<Player>> filterPredicate, Pair<String, Comparator<Player>> comparator) {
    super(contentId);
    List<Player> players = contest.getPlayers().stream()
        .filter(filterPredicate.getValue())
        .sorted(comparator.getValue())
        .collect(Collectors.toList());
    add(ComponentFactory.link("addPlayerLink", components -> setResponsePage(PlayerAddPage.class)));
    add(new DownloadLink("exportPlayerLink", LambdaModel.of(() -> makeFile(contest, players))));

    /* table head order and filter links */
    add(ComponentFactory.link("sortName", parameters("order", "name", comparator.getKey())));
    add(ComponentFactory.link("sortSurname", parameters("order", "surname", comparator.getKey())));
    add(ComponentFactory.link("sortClub", parameters("order", "club", comparator.getKey())));
    add(ComponentFactory.link("sortRank", parameters("order", "rank", comparator.getKey())));
    add(ComponentFactory.link("sortPayment", parameters("order", "payment", comparator.getKey())));

    /* table body */
    add(new PlayerListView("players", players));
    createSummary(contest, players);
  }

  private void createSummary(Contest contest, List<Player> players) {
    EarningsOverview earningsOverview = contestService.getEarningsOverview(contest);
    add(new Label("playerNum", players.size()));
    add(new Label("seminarSum", players.stream().filter(Player::isSeminarMember).count()));
    add(new Label("breakfastNum", LambdaModel.of(earningsOverview::getBreakfastCount)));
    add(new Label("totalCurrentRev", LambdaModel.of(earningsOverview::getTotalCurrentEarnings)));
    add(new Label("totalRev", LambdaModel.of(earningsOverview::getTotalEarnings)));
    add(new Label("startingFees", LambdaModel.of(earningsOverview::getStartFeeEarnings)));
    add(new Label("breakfastFees", LambdaModel.of(earningsOverview::getBreakfastEarnings)));
    add(new Label("sumClubs", players.stream().map(Player::getGoClub).distinct().count()));
  }

  private SerializableConsumer<Link<Void>> parameters(String name, String value, String currentValue) {
    PageParameters params = new PageParameters().add(name, value);
    if (value.equals(currentValue)) {
      params.add("orderDir", "reverse");
    }
    return components -> setResponsePage(PlayerManagementPage.class, params);
  }

  private File makeFile(Contest contest, Collection<Player> players) {
    try {
      PlayerExporter playerExporter = new PlayerExporter(players);
      Path tmpFile = Files.createTempFile(contest.getUniqueId() + "_player_export", ".csv");
      Files.write(tmpFile, playerExporter.getLines());
      LOGGER.info("Created Export at: {}", tmpFile.toAbsolutePath().toString());
      return tmpFile.toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
