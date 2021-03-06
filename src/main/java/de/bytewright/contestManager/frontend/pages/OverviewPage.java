package de.bytewright.contestManager.frontend.pages;

import java.util.List;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.frontend.components.overview.OverviewPanel;
import de.bytewright.contestManager.frontend.components.template.GcmTemplate;
import de.bytewright.contestManager.frontend.res.css.Marker;

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
  protected List<ResourceReference> getHeaderRenderContent(IHeaderResponse response) {
    return List.of(new PackageResourceReference(Marker.class, "style.css"));
  }

  @Override
  protected Component getContent(String contentId) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    return new OverviewPanel(contentId, contest);
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    return getContent(contentId);
  }
}
