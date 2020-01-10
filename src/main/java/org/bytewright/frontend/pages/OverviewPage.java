package org.bytewright.frontend.pages;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.overview.OverviewPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;

public class OverviewPage extends GcmTemplate {
  private static final long serialVersionUID = 1L;

  public OverviewPage() {
    super();
  }

  public OverviewPage(final PageParameters parameters) {
    super(parameters);
  }

  public static String getMountPath() {
    return "/overview";
  }

  @Override
  protected Component getContent(String contentId) {
    Optional<Contest> selectedContest = SessionInfo.getSSelectedContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    return new OverviewPanel(contentId, contest);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
