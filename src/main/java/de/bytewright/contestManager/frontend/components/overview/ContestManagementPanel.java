package de.bytewright.contestManager.frontend.components.overview;

import java.time.Duration;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.ContestSettings;

public class ContestManagementPanel extends Panel {
  public ContestManagementPanel(String contentId, Contest contest) {
    super(contentId);
    ContestSettings settings = contest.getContestSettings();
    add(new Label("contestName", settings.getName()));
    add(new Label("contestStartDate", settings.getDateStart()));
    add(new Label("contestEndDate", settings.getDateEnd()));
    add(new Label("contestDuration", Duration.between(settings.getDateStart(), settings.getDateEnd()).toDays()));
    add(new Label("contestLocation", settings.getLocation().getName()));
    add(new Label("contestRounds", settings.getRoundCount()));

  }
}
