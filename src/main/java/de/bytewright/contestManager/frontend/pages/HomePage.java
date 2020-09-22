package de.bytewright.contestManager.frontend.pages;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.backend.services.ContestService;
import de.bytewright.contestManager.frontend.components.home.HomePanel;
import de.bytewright.contestManager.frontend.components.template.GcmTemplate;

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
  protected Component getContent(String contentId, PageParameters parameters) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();

    if (selectedContest.isPresent() && !contestService.isValid(selectedContest.get())) {
      LOGGER.info("Users selected contest not in valid contests, deleting this as selected: {}", selectedContest);
      GoContestManagerSession.get().setContest(null);
    }
    return new HomePanel(CONTENT_ID, contestService.getValidContests(), selectedContest);
  }

  @Override
  protected Component getContent(String contentId) {
    return getContent(contentId, null);
  }
}
