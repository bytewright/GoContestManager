package org.bytewright.frontend.pages;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.wicket.Component;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.home.HomePanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@WicketHomePage
public class HomePage extends GcmTemplate {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
  private static final long serialVersionUID = 1L;

  @SpringBean
  private ContestService contestService;

  public static String getMountPath() {
    return "/home";
  }

  @Override
  protected Component getContent(String contentId) {
    Optional<Contest> selectedContest = SessionInfo.getSSelectedContestOpt();
    List<Contest> validContests = contestService.getValidContests().stream()
        .sorted(Comparator.comparing(Contest::getDateStart))
        .collect(Collectors.toList());
    if (selectedContest.isPresent() && !validContests.contains(selectedContest.get())) {
      LOGGER.info("User has selected invalid contest, resetting: {}", selectedContest);
      SessionInfo.setSSelectedContest(null);
    }
    return new HomePanel(CONTENT_ID, validContests, selectedContest);
  }
}
