package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.bytewright.backend.dto.Contest;

public class OverviewPanel extends Panel {
  private static final long serialVersionUID = 1L;
  private static final String ID_CONTEST_MANAGEMENT = "contestManagement";

  public OverviewPanel(String contentId, Contest contest) {
    super(contentId);
    setDefaultModel(new CompoundPropertyModel<>(contest));
    add(new Label("contestName", LambdaModel.of(contest::getName, contest::setName)));
    add(new Label("contestDate", Model.of(contest.getDateStart())));
    add(new Label("playerCount", Model.of(contest.getPlayers().size())));
    add(new ContestManagementPanel(ID_CONTEST_MANAGEMENT, contest));
  }
}
