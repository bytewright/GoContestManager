package org.bytewright.frontend.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.HiSayer;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.services.PropertiesService;
import org.bytewright.frontend.components.home.HomePanel;
import org.bytewright.frontend.components.template.GcmTemplate;

@WicketHomePage
public class HomePage extends GcmTemplate {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private ContestService contestService;
  @SpringBean
  private HiSayer hiSayer;
  @SpringBean
  private PropertiesService propertiesService;

  public HomePage() {
    super();
  }

  public HomePage(final PageParameters parameters) {
    super(parameters);
  }

  @Override
  protected Component getContent(String contentId) {
    return new HomePanel(CONTENT_ID, propertiesService, hiSayer, contestService.getValidContests());
  }
}
