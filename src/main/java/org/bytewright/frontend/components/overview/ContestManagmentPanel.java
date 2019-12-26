package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.bytewright.backend.dto.Contest;

public class ContestManagmentPanel extends Panel {
  public ContestManagmentPanel(String contentId, Contest contest) {
    super(contentId);

    add(new Label("contestName", contest.getName()));
    add(new Label("contestStartDate", contest.getStartDate()));
    add(new Label("contestEndDate", contest.getEndDate()));
    add(new Label("contestLocation", contest.getLocation().getName()));

  }
}
