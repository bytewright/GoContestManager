package org.bytewright.frontend.pages;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.frontend.components.manage.players.PlayerAddPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class PlayerAddPage extends GcmTemplate {

  public static String getMountPath() {
    return "/players/add";
  }

  @Override
  protected Component getContent(String contentId) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    return new PlayerAddPanel(contentId, contest);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
    cssFile = new PackageResourceReference(Marker.class, "div-as-table.css");
    response.render(CssHeaderItem.forReference(cssFile));
    cssFile = new PackageResourceReference(Marker.class, "feedbackPanel.css");
    response.render(CssHeaderItem.forReference(cssFile));
  }
}
