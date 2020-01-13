package org.bytewright.backend.security;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.bytewright.backend.persistance.entities.User;
import org.bytewright.backend.persistance.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;

/**
 * https://github.com/ceefour/paxwicket-shiro/blob/master/app/src/main/java/com/hendyirawan/paxwicketshiro/app/ShiroRealm.java
 */
@Configuration
public class ShiroContextConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroContextConfiguration.class);
  @Autowired
  private UserRepository userRepository;

  @Bean
  public Realm realm() {
    // TODO actuall security with JPA: org.apache.shiro.realm.jdbc.JdbcRealm
    TextConfigurationRealm realm = new TextConfigurationRealm();
    realm.setUserDefinitions(""
        + "admin=secret,admin\n"
        + "guest=guestPassword");

    realm.setRoleDefinitions(""
        + "admin=*\n"
        + "user=read");
    realm.setCachingEnabled(false);
    LOGGER.debug("Created Shiro Security Realm: {}", realm);

    LOGGER.warn("found {} users", userRepository.count());
    for (User user : userRepository.findAll()) {
      LOGGER.warn("found users: {}", user);
    }

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
