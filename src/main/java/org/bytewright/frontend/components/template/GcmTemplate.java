package org.bytewright.frontend.components.template;

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
  private Component footerPanel;

  public GcmTemplate() {
    super();
    add(headerPanel = new HeaderPanel("headerMenu"));
    add(footerPanel = new FooterPanel("footerPanel"));
    add(getContent(CONTENT_ID));
  }

  public GcmTemplate(PageParameters parameters) {
    super(parameters);
    add(headerPanel = new HeaderPanel("headerMenu"));
    add(footerPanel = new FooterPanel("footerPanel"));
    add(getContent(CONTENT_ID));
  }

  protected Component getContent(String contentId) {
    return new Label(CONTENT_ID, "Put your content here");
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

  public Component getFooterPanel() {
    return footerPanel;
  }
}
