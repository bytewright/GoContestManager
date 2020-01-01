package org.bytewright.backend;

import org.bytewright.backend.util.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Properties;

@Configuration
public class BackendConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(BackendConfig.class);

  @ConditionalOnMissingBean
  @Bean
  public BuildProperties buildProperties() throws Exception {
    /* Needed until maven install is called once */
    Properties props = new Properties();
    props.put("devMode", true);
    LOGGER.info("BuildProperties Bean is missing, creating fake");
    return new BuildProperties(props);
  }

  @Bean
  @SessionScope
  public SessionInfo sessionInfo() {
    return new SessionInfo();
  }
}
