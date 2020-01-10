package org.bytewright.backend.security;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;

/**
 * https://github.com/ceefour/paxwicket-shiro/blob/master/app/src/main/java/com/hendyirawan/paxwicketshiro/app/ShiroRealm.java
 */
@Configuration
public class ShiroContextConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroContextConfiguration.class);

  @Bean
  public Realm realm() {
    TextConfigurationRealm realm = new TextConfigurationRealm();
    realm.setUserDefinitions(""
        + "admin=secret,admin\n"
        + "guest=guestPassword");

    realm.setRoleDefinitions(""
        + "admin=*\n"
        + "user=read");
    realm.setCachingEnabled(true);
    LOGGER.debug("Created Realm: {}", realm);
    return realm;
  }

  @Bean
  public AuthenticatedWebSessionConfig authenticatedWebSessionConfig() {
    return new MyAuthenticatedWebSessionConfig();
  }

  static class MyAuthenticatedWebSessionConfig implements AuthenticatedWebSessionConfig {

    @Override
    public Class<? extends AbstractAuthenticatedWebSession> getAuthenticatedWebSessionClass() {
      return GoContestManagerSession.class;
    }
  }
}
