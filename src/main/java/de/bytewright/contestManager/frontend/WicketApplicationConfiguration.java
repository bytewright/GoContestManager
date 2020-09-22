package de.bytewright.contestManager.frontend;

import java.time.Instant;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.resource.loader.BundleStringResourceLoader;
import org.apache.wicket.settings.ResourceSettings;
import org.apache.wicket.settings.SecuritySettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.backend.services.ContestService;
import de.bytewright.contestManager.frontend.pages.LoginPage;
import de.bytewright.contestManager.frontend.pages.PageMountRegistry;
import de.bytewright.contestManager.frontend.pages.admin.SecuredPage;

@ApplicationInitExtension
public class WicketApplicationConfiguration extends WicketBootSecuredWebApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(WicketApplicationConfiguration.class);
  public static final String BUNDLE_NAME = "org.bytewright.WicketApplicationGCM";
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
    LOGGER.info("Loading translations from bundle {}", BUNDLE_NAME);
    resourceSettings.getStringResourceLoaders().add(new BundleStringResourceLoader(BUNDLE_NAME));
  }
}
