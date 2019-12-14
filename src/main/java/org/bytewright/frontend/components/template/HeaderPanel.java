package org.bytewright.frontend.components.template;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;

public class HeaderPanel extends Panel {
  public HeaderPanel(String id) {
    super(id);
    //   BookmarkablePageLink bpl = new BookmarkablePageLink<Void>("home", HomePage.class, new PageParameters());
    // add(bpl);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(HeaderPanel.class, "naviStyle.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
