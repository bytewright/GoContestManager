package org.bytewright.frontend.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.bytewright.frontend.pages.template.GcmTemplate;

public class TestHelloPage extends GcmTemplate {
  public TestHelloPage() {
    super();
    replace(new Label(CONTENT_ID, "my content"));
  }
}
