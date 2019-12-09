package org.bytewright.backend.security;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class ShiroAuthenticatedWebSession extends AbstractAuthenticatedWebSession {
  /**
   * Construct.
   *
   * @param request
   *            The current request object
   */
  public ShiroAuthenticatedWebSession(Request request) {
    super(request);
  }

  @Override
  public Roles getRoles() {
    return new Roles(Roles.ADMIN);
  }

  @Override
  public boolean isSignedIn() {
    return true;
  }
}
