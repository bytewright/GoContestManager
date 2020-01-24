package org.bytewright.frontend.components.overview;

import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.EarningsOverview;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.services.ContestService;

public class PlayerSummaryPanel extends Panel {
  @SpringBean
  private ContestService contestService;

  public PlayerSummaryPanel(String contentId, Contest contest) {
    super(contentId);
    Set<Player> players = contest.getPlayers();
    EarningsOverview earningsOverview = contestService.getEarningsOverview(contest);
    add(new Label("playerNum", players.size()));
    add(new Label("seminarSum", players.stream().filter(Player::isSeminarMember).count()));
    add(new Label("breakfastNum", LambdaModel.of(earningsOverview::getBreakfastCount)));
    add(new Label("totalCurrentRev", LambdaModel.of(earningsOverview::getTotalCurrentEarnings)));
    add(new Label("totalRev", LambdaModel.of(earningsOverview::getTotalEarnings)));
    add(new Label("startingFees", LambdaModel.of(earningsOverview::getStartFeeEarnings)));
    add(new Label("breakfastFees", LambdaModel.of(earningsOverview::getBreakfastEarnings)));
    add(new Label("clubsNum", players.stream().map(Player::getGoClub).distinct().count()));
  }
}
