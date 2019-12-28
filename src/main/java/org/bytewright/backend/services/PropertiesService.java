package org.bytewright.backend.services;

import org.bytewright.frontend.WicketApplicationConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PropertiesService {
  private final BuildProperties buildProperties;
  private final WicketApplicationConfiguration wicketApplicationConfiguration;

  public PropertiesService(BuildProperties buildProperties, WicketApplicationConfiguration wicketApplicationConfiguration) {
    this.buildProperties = buildProperties;
    this.wicketApplicationConfiguration = wicketApplicationConfiguration;
  }

  public String getAppVersion() {
    return Optional.ofNullable(buildProperties.getVersion()).orElse("DEV");
  }

  public String getSpringVersion() {
    return Optional.ofNullable(buildProperties.get("spring.version")).orElse("DEV");
  }

  public String getWicketVersion() {
    return Optional.ofNullable(wicketApplicationConfiguration.getFrameworkSettings().getVersion())
      .orElse("DEV");
  }
}
