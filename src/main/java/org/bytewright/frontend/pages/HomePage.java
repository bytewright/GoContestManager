package org.bytewright.frontend.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.HiSayer;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@WicketHomePage
public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private HiSayer hiSayer;

  public HomePage(final PageParameters parameters) {
    super(parameters);

    add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    add(new Label("word", hiSayer.sayHi()));

    // TODO Add your page's components here

  }
}
