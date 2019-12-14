package org.bytewright;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WicketFilter;
import org.bytewright.frontend.WicketApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {
  private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApp.class);

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder()
        .logStartupInfo(true)
        .addCommandLineProperties(true)
        .sources(SpringBootApp.class)
        .run(args);
  }

  @Bean
  public FilterRegistrationBean<WicketFilter> wicketFilterRegistration(WicketFilter wicketFilter,
      WicketApplicationConfiguration wicketApplication) {
    FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(wicketFilter);
    registration.addUrlPatterns("/*");
    registration.addInitParameter("applicationClassName", wicketApplication.getClass().getCanonicalName());
    registration.addInitParameter("configuration", RuntimeConfigurationType.DEVELOPMENT.name());
    registration.setName("myWicketFilter");
    registration.setOrder(1);
    LOGGER.info("Created Wicket Filter Registration: {}", registration);
    return registration;
  }
}
