package org.bytewright.frontend.pages.template;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

public class GcmTemplate extends WebPage {
  protected static final String CONTENT_ID = "contentComponent";

  private Component headerPanel;
  private Component menuPanel;
  private Component footerPanel;

  public GcmTemplate(PageParameters parameters) {
    super(parameters);
    add(headerPanel = new HeaderPanel("headerPanel"));
    add(menuPanel = new MenuPanel("menuPanel"));
    add(footerPanel = new FooterPanel("footerPanel"));
    add(new Label(CONTENT_ID, "Put your content here"));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(GcmTemplate.class,
        "baseStyle.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }

  public Component getHeaderPanel() {
    return headerPanel;
  }

  public Component getMenuPanel() {
    return menuPanel;
  }

  public Component getFooterPanel() {
    return footerPanel;
  }
}
