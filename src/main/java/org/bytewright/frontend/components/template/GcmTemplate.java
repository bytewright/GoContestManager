package org.bytewright.frontend.components.template;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.frontend.res.css.Marker;

public class GcmTemplate extends WebPage {
  protected static final String CONTENT_ID = "contentComponent";
  private Component footerPanel;
  private Component headerPanel;

  public GcmTemplate() {
    headerPanel = new HeaderPanel("headerMenu");
    footerPanel = new FooterPanel("footerPanel");
    add(headerPanel);
    add(getContent(CONTENT_ID));
    add(footerPanel);
  }

  public GcmTemplate(PageParameters parameters) {
    super(parameters);
    headerPanel = new HeaderPanel("headerMenu");
    footerPanel = new FooterPanel("footerPanel");
    add(headerPanel);
    add(getContent(CONTENT_ID, parameters));
    add(footerPanel);
    setStatelessHint(true);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "baseStyle.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }

  public Component getHeaderPanel() {
    return headerPanel;
  }

  public Component getFooterPanel() {
    return footerPanel;
  }

  protected Component getContent(String contentId, PageParameters parameters) {
    return getContent(contentId);
  }

  protected Component getContent(String contentId) {
    return new Label(CONTENT_ID, "Put your content here");
  }
}
