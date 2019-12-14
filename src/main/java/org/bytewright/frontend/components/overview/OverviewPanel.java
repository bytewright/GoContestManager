package org.bytewright.frontend.components.overview;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.HiSayer;
import org.bytewright.frontend.res.css.Marker;

public class OverviewPanel extends Panel {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private HiSayer hiSayer;

  public OverviewPanel(String contentId) {
    super(contentId);

    add(new Label("contestName", "JCC - Jena CrossCut"));
    add(new Label("contestDate", "20.09.2020"));
    add(new Label("playerCount", "55"));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
