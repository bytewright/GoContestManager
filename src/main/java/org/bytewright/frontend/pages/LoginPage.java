package org.bytewright.frontend.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;
import org.wicketstuff.shiro.component.LoginPanel;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;

@WicketSignInPage
public class LoginPage extends GcmTemplate {

  public static String getMountPath() {
    return "/login";
  }

  @Override
  protected Component getContent(String contentId) {
    return new LoginPanel(contentId, true);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
