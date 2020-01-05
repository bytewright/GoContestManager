package org.bytewright.frontend.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.UserService;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.manage.contests.ContestSettingsPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

import java.util.Optional;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ContestSettingsPage extends GcmTemplate {

  @SpringBean
  private UserService userService;

  public static String getMountPath() {
    return "/contests/edit";
  }

  @Override
  protected Component getContent(String contentId) {
    return getContent(contentId, new PageParameters());
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    SessionInfo sessionInfo = userService.getSessionInfo();
    Optional<Contest> selectedContest = sessionInfo.getSelectedContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    return new ContestSettingsPanel(contentId, contest);
  }
}
