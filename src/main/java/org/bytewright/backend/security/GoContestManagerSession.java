package org.bytewright.backend.security;

import java.time.Instant;
import java.util.Optional;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.bytewright.backend.dto.Contest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoContestManagerSession extends AbstractAuthenticatedWebSession {
  private static final Logger LOGGER = LoggerFactory.getLogger(GoContestManagerSession.class);
  private Contest contest;
  private Instant creationInstant;

  public GoContestManagerSession(Request request) {
    super(request);
    LOGGER.warn("Created new Session obj for request {}", request.getUrl());
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

  public Contest getContest() {
    return Optional.ofNullable(contest).orElseThrow(() -> new IllegalStateException("Selected contest is not set!"));
  }

  public void setContest(Contest contest) {
    this.contest = contest;
  }

  public Instant getCreationInstant() {
    return creationInstant;
  }

  public void setCreationInstant(Instant creationInstant) {
    this.creationInstant = creationInstant;
  }

  /**
   * Return session of current user, provided by wicket
   */
  public static GoContestManagerSession get() {
    Session session = Session.get();
    if (session instanceof GoContestManagerSession) {
      return (GoContestManagerSession) session;
    }
    throw new IllegalStateException("Session is of unexpected class: " + session.getClass());
  }

  public Optional<Contest> getContestOpt() {
    return Optional.ofNullable(contest);
  }
}