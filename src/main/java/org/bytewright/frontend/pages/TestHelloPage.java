package org.bytewright.frontend.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.components.template.GcmTemplate;

public class TestHelloPage extends GcmTemplate {
  public TestHelloPage(PageParameters parameters) {
    super(parameters);
    replace(new Label(CONTENT_ID, "my content"));
  }
}
