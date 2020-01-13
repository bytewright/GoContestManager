package org.bytewright.frontend.pages;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.frontend.components.manage.contests.ContestSettingsPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ContestSettingsPage extends GcmTemplate {

  public static String getMountPath() {
    return "/contests/edit";
  }

  @Override
  protected Component getContent(String contentId) {
    return getContent(contentId, new PageParameters());
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    return new ContestSettingsPanel(contentId, contest);
  }
}
