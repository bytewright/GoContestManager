package org.bytewright.testing;

import org.apache.wicket.protocol.http.WebApplication;
import org.bytewright.frontend.WicketApplicationConfiguration;
import org.bytewright.frontend.pages.PageMountRegistry;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@Configuration
@ComponentScan({ "org.bytewright" })
public class SpringTestContext {
  @Bean
  @Primary
  public WebApplication webApplication(PageMountRegistry pageMountRegistry){
    return new WicketApplicationConfiguration(pageMountRegistry);
  }
  @Bean
  public BuildProperties buildProperties(){
    return new BuildProperties(new Properties());
  }
}
