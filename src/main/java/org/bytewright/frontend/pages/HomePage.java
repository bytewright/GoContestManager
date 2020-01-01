package org.bytewright.frontend.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.services.PropertiesService;
import org.bytewright.backend.services.UserService;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.home.HomePanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@WicketHomePage
public class HomePage extends GcmTemplate {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
  private static final long serialVersionUID = 1L;

  @SpringBean
  private ContestService contestService;
  @SpringBean
  private PropertiesService propertiesService;
  @SpringBean
  private UserService userService;

  public HomePage() {
    super();
  }

  public HomePage(final PageParameters parameters) {
    super(parameters);
  }

  @Override
  protected Component getContent(String contentId) {
    SessionInfo sessionInfo = userService.getSessionInfo();
    List<Contest> validContests = contestService.getValidContests();
    if (sessionInfo.getSelectedContest() != null &&
      !validContests.contains(sessionInfo.getSelectedContest())) {
      LOGGER.info("User has selected invalid contest, resetting: {}", sessionInfo.getSelectedContest());
      sessionInfo.setSelectedContest(null);
    }
    LOGGER.warn("{}", sessionInfo);
    return new HomePanel(CONTENT_ID, propertiesService, validContests);
  }
}
