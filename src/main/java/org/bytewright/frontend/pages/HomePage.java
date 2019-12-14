package org.bytewright.frontend.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.components.home.HomePanel;
import org.bytewright.frontend.components.template.GcmTemplate;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@WicketHomePage
public class HomePage extends GcmTemplate {
  private static final long serialVersionUID = 1L;

  public HomePage(final PageParameters parameters) {
    super(parameters);

    replace(new HomePanel(CONTENT_ID));

  }
}
