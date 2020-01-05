package org.bytewright.frontend.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.components.manage.contests.ContestCreationPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ContestCreationPage extends GcmTemplate {
  public ContestCreationPage(PageParameters parameters) {
    super(parameters);
    replace(new ContestCreationPanel(CONTENT_ID, parameters));
  }

  public static String getMountPath() {
    return "/contests/create";
  }
}
