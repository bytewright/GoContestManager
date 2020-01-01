package org.bytewright.backend.services;

import org.bytewright.backend.util.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;

@Component
public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
  @Resource
  private SessionInfo sessionInfo;

  public SessionInfo getSessionInfo() {
    if (sessionInfo.getCreationInstant() == null) {
      Instant now = Instant.now();
      LOGGER.warn("detected new session at {}", now);
      sessionInfo.setCreationInstant(now);
    }
    return sessionInfo;
  }
}
