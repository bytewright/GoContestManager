package org.bytewright.frontend.pages;

import java.util.Optional;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.UserService;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.overview.OverviewPanel;
import org.bytewright.frontend.components.template.GcmTemplate;

public class OverviewPage extends GcmTemplate {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private UserService userService;

  public OverviewPage() {
    this(new PageParameters());
  }

  public OverviewPage(final PageParameters parameters) {
    super(parameters);
    SessionInfo sessionInfo = userService.getSessionInfo();
    Optional<Contest> selectedContest = sessionInfo.getSelectedContest();
    if (selectedContest.isEmpty()) {
      replace(new Label(CONTENT_ID, "Select a contest on the homepage!"));
      return;
    }
    Contest contest = selectedContest.get();
    replace(new OverviewPanel(CONTENT_ID, contest));
  }

  public static String getMountPath() {
    return "/overview";
  }

}
