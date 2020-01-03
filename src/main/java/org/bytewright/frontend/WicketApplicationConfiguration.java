package org.bytewright.frontend;

import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.settings.SecuritySettings;
import org.bytewright.frontend.pages.LoginPage;
import org.bytewright.frontend.pages.PageMountRegistry;
import org.bytewright.frontend.pages.admin.SecuredPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

@ApplicationInitExtension
public class WicketApplicationConfiguration extends WicketBootSecuredWebApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(WicketApplicationConfiguration.class);

  private final PageMountRegistry pageMountRegistry;

  @Autowired
  public WicketApplicationConfiguration(PageMountRegistry pageMountRegistry) {
    this.pageMountRegistry = pageMountRegistry;
  }

  @Override
  protected void init() {
    super.init();

    LOGGER.info("Init of WicketApplication, context is: {}", getApplicationContext());

    // Enable Shiro security
    AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
    SecuritySettings securitySettings = getSecuritySettings();
    securitySettings.setAuthorizationStrategy(authz);
    ShiroUnauthorizedComponentListener listener = new ShiroUnauthorizedComponentListener(
        LoginPage.class, AccessDeniedPage.class, authz);
    securitySettings.setUnauthorizedComponentInstantiationListener(listener);

    pageMountRegistry.getMountables().stream()
        .peek(mountable -> LOGGER.info("registering mount: {}", mountable))
        .forEach(mountable -> mountPage(mountable.getMountPath(), mountable.getPageClass()));

    //    mountPage("home", getHomePage());
    mountPage("secure", SecuredPage.class);
  }
}
