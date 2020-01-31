package org.bytewright.frontend;

import java.time.Instant;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.resource.loader.BundleStringResourceLoader;
import org.apache.wicket.settings.ResourceSettings;
import org.apache.wicket.settings.SecuritySettings;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.backend.services.ContestService;
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

  @Autowired
  private ContestService contestService;

  @Override
  public Session newSession(Request request, Response response) {
    /* class set by org.bytewright.backend.security.ShiroContextConfiguration.MyAuthenticatedWebSessionConfig.getAuthenticatedWebSessionClass */
    GoContestManagerSession session = (GoContestManagerSession) super.newSession(request, response);
    Instant now = Instant.now();
    session.setCreationInstant(now);
    session.setContest(contestService.getNextContest());
    session.setUserDbId(-1);
    return session;
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

    PageMountRegistry.getMountables().stream()
        .peek(mountable -> LOGGER.info("registering mount: {}", mountable))
        .forEach(mountable -> mountPage(mountable.getMountPath(), mountable.getPageClass()));
    mountPage("secure", SecuredPage.class);
    ResourceSettings resourceSettings = getResourceSettings();
    resourceSettings.getStringResourceLoaders()
        .add(new BundleStringResourceLoader("org.bytewright.WicketApplicationGCM"));
  }
}
