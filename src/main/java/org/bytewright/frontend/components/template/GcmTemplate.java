package org.bytewright.frontend.components.template;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.bytewright.frontend.res.css.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GcmTemplate extends WebPage {
  private static final Logger LOGGER = LoggerFactory.getLogger(GcmTemplate.class);
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
    Stream.concat(
        getBaseCss().stream(),
        getHeaderRenderContent(response).stream())
        .peek(resourceReference -> LOGGER.trace("Rendering {} to head", resourceReference.getName()))
        .map(CssHeaderItem::forReference)
        .forEach(response::render);
  }

  private Collection<PackageResourceReference> getBaseCss() {
    return List.of(
        new PackageResourceReference(Marker.class, "baseStyle.css"),
        new PackageResourceReference(Marker.class, "naviStyle.css")
    );
  }

  protected List<ResourceReference> getHeaderRenderContent(IHeaderResponse response) {
    return List.of();
  }

  public Component getHeaderPanel() {
    return headerPanel;
  }

  public Component getFooterPanel() {
    return footerPanel;
  }

  protected abstract Component getContent(String contentId, PageParameters parameters);

  protected Component getContent(String contentId) {
    return new Label(CONTENT_ID, "Put your content here");
  }
}
