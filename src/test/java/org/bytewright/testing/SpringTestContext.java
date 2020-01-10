package org.bytewright.testing;

import java.util.Properties;

import org.apache.wicket.protocol.http.WebApplication;
import org.bytewright.frontend.WicketApplicationConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({ "org.bytewright" })
public class SpringTestContext {
  @Bean
  @Primary
  public WebApplication webApplication() {
    return new WicketApplicationConfiguration();
  }

  @Bean
  public BuildProperties buildProperties() {
    return new BuildProperties(new Properties());
  }
}
