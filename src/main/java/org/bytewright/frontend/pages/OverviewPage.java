package org.bytewright.frontend.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.components.overview.OverviewPanel;
import org.bytewright.frontend.components.template.GcmTemplate;

public class OverviewPage extends GcmTemplate {
  private static final long serialVersionUID = 1L;

  public OverviewPage(final PageParameters parameters) {
    super(parameters);
    replace(new OverviewPanel(CONTENT_ID));
  }
}
