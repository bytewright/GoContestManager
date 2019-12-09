package org.bytewright.backend.security;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.wicket.spring.boot.context.security.AuthenticatedWebSessionConfig;

@Configuration
public class ShiroConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

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
    return () -> ShiroAuthenticatedWebSession.class;
  }

  //
  //  @Bean
  //  public ShiroFilterChainDefinition shiroFilterChainDefinition() {
  //    /* https://shiro.apache.org/web.html#web-ini-configuration */
  //    DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
  //
  //    // logged in users with the 'admin' role
  //    chainDefinition.addPathDefinition("/admin/**", DefaultFilter.authc.name() + ", roles[admin]");
  //
  //    // logged in users with the 'document:read' permission
  //    chainDefinition.addPathDefinition("/docs/**", DefaultFilter.authc.name() + ", perms[document:read]");
  //
  //    // all other paths are open
  //    chainDefinition.addPathDefinition("/**", DefaultFilter.anon.name());
  //    LOGGER.debug("Created Filter chain: {}", chainDefinition);
  //    return chainDefinition;
  //  }
}
