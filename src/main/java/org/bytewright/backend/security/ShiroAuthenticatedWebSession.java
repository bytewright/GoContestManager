package org.bytewright.backend.security;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroAuthenticatedWebSession extends AbstractAuthenticatedWebSession {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroAuthenticatedWebSession.class);

  public ShiroAuthenticatedWebSession(Request request) {
    super(request);
  }

  @Override
  public Roles getRoles() {
    LOGGER.info("Roles are requested");
    return new Roles(Roles.ADMIN);
  }

  @Override
  public boolean isSignedIn() {
    LOGGER.info("isSignedIn is requested");
    return true;
  }
}
