package org.bytewright.frontend;

import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.bytewright.frontend.pages.LoginPage;
import org.bytewright.frontend.pages.SecuredPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

@ApplicationInitExtension
@Component
public class WicketApplicationConfiguration extends WicketBootSecuredWebApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(WicketApplicationConfiguration.class);

  @Override
  protected void init() {
    super.init();

    LOGGER.info("Init of WicketApplication, context is: {}", getApplicationContext());

    // Enable Shiro security
    AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
    getSecuritySettings().setAuthorizationStrategy(authz);
    getSecuritySettings().setUnauthorizedComponentInstantiationListener(
        new ShiroUnauthorizedComponentListener(LoginPage.class, AccessDeniedPage.class, authz));

    mountPage("login", LoginPage.class);
    mountPage("secure", SecuredPage.class);
  }
}
