package org.bytewright.frontend.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;
import org.wicketstuff.shiro.page.LogoutPage;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class SecuredPage extends WebPage {

  private static final long serialVersionUID = 1L;

  public SecuredPage() {
    Subject subject = SecurityUtils.getSubject();
    add(new Label("name", (String) subject.getPrincipal()));

    Link<Void> logoutLink = new Link<Void>("logoutLink") {

      private static final long serialVersionUID = 1L;

      @Override
      public void onClick() {
        new LogoutPage(HomePage.class);
      }
    };
    add(logoutLink);

    if (subject.isPermitted("war:start")) {
      add(new Label("startWar", "You can start a war"));
    } else {
      add(new Label("startWar", "You can't start a war"));
    }

    if (subject.isPermitted("war:end")) {
      add(new Label("endWar", "You can end a war"));
    } else {
      add(new Label("endWar", "You can't end a war"));
    }

    if (subject.isPermitted("war:watch")) {
      add(new Label("watchWar", "You can watch a war"));
    } else {
      add(new Label("watchWar", "You can't watch a war"));
    }
  }

}