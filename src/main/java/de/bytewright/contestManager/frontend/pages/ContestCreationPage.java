package de.bytewright.contestManager.frontend.pages;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

import de.bytewright.contestManager.frontend.components.manage.contests.ContestCreationPanel;
import de.bytewright.contestManager.frontend.components.template.GcmTemplate;

@ShiroSecurityConstraint(constraint = ShiroConstraint.HasPermission, value = "contest:create", loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ContestCreationPage extends GcmTemplate {
  @Override
  protected Component getContent(String contentId) {
    return getContent(contentId, null);
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    return new ContestCreationPanel(contentId, parameters);
  }

  public static String getMountPath() {
    return "/contests/create";
  }
}
