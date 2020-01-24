package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.ContestSettings;

public class OverviewPanel extends Panel {
  private static final long serialVersionUID = 1L;
  private static final String ID_CONTEST_MANAGEMENT = "contestManagement";

  public OverviewPanel(String contentId, Contest contest) {
    super(contentId);
    setDefaultModel(new CompoundPropertyModel<>(contest));
    ContestSettings settings = contest.getContestSettings();
    add(new Label("contestName", LambdaModel.of(settings::getName)));
    add(new Label("contestDate", Model.of(settings.getDateStart())));
    add(new Label("playerCount", Model.of(contest.getPlayers().size())));
    add(new ContestManagementPanel(ID_CONTEST_MANAGEMENT, contest));
  }
}
