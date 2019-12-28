package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.backend.dto.Contest;
import org.bytewright.frontend.res.css.Marker;

public class OverviewPanel extends Panel {
  private static final long serialVersionUID = 1L;
  private static final String ID_PLAYER_MANAGMENT = "playerManagment";
  private static final String ID_CONTEST_MANAGMENT = "contestManagment";

  public OverviewPanel(String contentId, Contest contest) {
    super(contentId);
    setDefaultModel(new CompoundPropertyModel<>(contest));
    add(new Label("contestName", LambdaModel.of(contest::getName, contest::setName)));
    add(new Label("contestDate", Model.of(contest.getDateStart())));
    add(new Label("playerCount", Model.of(contest.getPlayers().size())));
    add(new PlayerManagmentPanel(ID_PLAYER_MANAGMENT, contest));
    add(new ContestManagmentPanel(ID_CONTEST_MANAGMENT, contest));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
