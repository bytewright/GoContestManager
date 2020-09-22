package de.bytewright.contestManager.frontend.pages.admin;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

import de.bytewright.contestManager.frontend.components.admin.AdminUserPanel;
import de.bytewright.contestManager.frontend.components.template.GcmTemplate;

@ShiroSecurityConstraint(constraint = ShiroConstraint.HasRole, value = "admin", loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class ManageSiteUsers extends GcmTemplate {

  public static String getMountPath() {
    return "/admin/users/manage";
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    return new AdminUserPanel(contentId);
  }

  @Override
  protected Component getContent(String contentId) {
    return getContent(contentId, null);
  }
}
