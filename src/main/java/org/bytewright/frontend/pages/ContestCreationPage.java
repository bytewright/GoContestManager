package org.bytewright.frontend.pages;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.components.manage.contests.ContestCreationPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.HasPermission, value = "contest:create", loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ContestCreationPage extends GcmTemplate {

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    return new ContestCreationPanel(contentId, parameters);
  }

  public static String getMountPath() {
    return "/contests/create";
  }
}
