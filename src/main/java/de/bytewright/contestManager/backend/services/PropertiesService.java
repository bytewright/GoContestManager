package de.bytewright.contestManager.backend.services;

import java.util.Optional;

import org.apache.wicket.WicketRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.frontend.WicketApplicationConfiguration;

@Component
public class PropertiesService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesService.class);
  @Autowired
  private BuildProperties buildProperties;
  @Autowired
  private WicketApplicationConfiguration wicketApplicationConfiguration;

  public String getAppVersion() {
    return Optional.ofNullable(buildProperties.getVersion()).orElse("DEV");
  }

  public String getSpringVersion() {
    return Optional.ofNullable(buildProperties.get("spring.version")).orElse("DEV");
  }

  public String getWicketVersion() {
    try {
      return Optional.ofNullable(wicketApplicationConfiguration.getFrameworkSettings().getVersion())
          .orElse("DEV");
    } catch (WicketRuntimeException exception) {
      LOGGER.warn("Exception while retrieving wicket Version: {}", exception.getMessage());
      return "DEV";
    }
  }
}
