package org.bytewright.frontend.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.ContestService;
import org.bytewright.frontend.components.overview.OverviewPanel;
import org.bytewright.frontend.components.template.GcmTemplate;

public class OverviewPage extends GcmTemplate {
  private static final long serialVersionUID = 1L;
  public static String PATH_PARAM = "contestIdentifier";

  @SpringBean
  private ContestService contestService;

  public OverviewPage() {
    super();
    replace(new Label(CONTENT_ID, "No content ID provided!"));
  }

  public OverviewPage(final PageParameters parameters) {
    super(parameters);
    String contestId = parameters.get(0).toString();
    Contest contest = contestService.getContest(contestId);
    replace(new OverviewPanel(CONTENT_ID, contest));
  }

  public static String getMountPath() {
    return "/${" + PATH_PARAM + "}/overview";
  }

}
