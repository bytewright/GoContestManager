package de.bytewright.contestManager.backend.security;

import org.apache.shiro.realm.Realm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;

/**
 * Here are all security relevant beans defined
 */
@Configuration
public class ShiroContextConfiguration {

  @Bean
  public Realm realm() {
    /* The 'login-space' for this application. This manages the logged in users and checks access privileges */
    return new ShiroJpaRepoRealm();
  }

  @Bean
  public AuthenticatedWebSessionConfig authenticatedWebSessionConfig() {
    return new MyAuthenticatedWebSessionConfig();
  }

  static class MyAuthenticatedWebSessionConfig implements AuthenticatedWebSessionConfig {

    @Override
    public Class<? extends AbstractAuthenticatedWebSession> getAuthenticatedWebSessionClass() {
      /* Each User of this app has a session and each session is an instance of this class */
      return GoContestManagerSession.class;
    }
  }
}
