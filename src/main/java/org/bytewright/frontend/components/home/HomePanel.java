package org.bytewright.frontend.components.home;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.HiSayer;
import org.bytewright.frontend.res.css.Marker;

public class HomePanel extends Panel {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private HiSayer hiSayer;

  public HomePanel(String contentId) {
    super(contentId);

    add(new Label("AppVersion", getApplication().getFrameworkSettings().getVersion()));
    add(new Label("WicketVersion", getApplication().getFrameworkSettings().getVersion()));
    add(new Label("SpringVersion", getApplication().getFrameworkSettings().getVersion()));
    add(new Label("springBeanTest", hiSayer.sayHi()));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
