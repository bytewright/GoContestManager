package org.bytewright.frontend.components.overview;

import java.time.Duration;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.bytewright.backend.dto.Contest;

public class ContestManagementPanel extends Panel {
  public ContestManagementPanel(String contentId, Contest contest) {
    super(contentId);

    add(new Label("contestName", contest.getName()));
    add(new Label("contestStartDate", contest.getDateStart()));
    add(new Label("contestEndDate", contest.getDateEnd()));
    add(new Label("contestDuration", Duration.between(contest.getDateStart(), contest.getDateEnd()).toDays()));
    add(new Label("contestLocation", contest.getLocation().getName()));
    add(new Label("contestRounds", contest.getContestSettings().getRoundCount()));

  }
}
