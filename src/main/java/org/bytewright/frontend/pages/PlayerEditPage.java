package org.bytewright.frontend.pages;

import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.services.UserService;
import org.bytewright.backend.util.SessionInfo;
import org.bytewright.frontend.components.manage.players.PlayerEditPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class PlayerEditPage extends GcmTemplate {

  @SpringBean
  private PersonService personService;
  @SpringBean
  private UserService userService;

  public PlayerEditPage(PageParameters parameters) {
    super(parameters);
  }

  public static String getMountPath() {
    return "/players/edit";
  }

  @Override
  protected Component getContent(String contentId, PageParameters pageParameters) {
    SessionInfo sessionInfo = userService.getSessionInfo();
    Optional<Contest> selectedContest = sessionInfo.getSelectedContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    Long playerId = pageParameters.get("playerId").to(Long.class);
    Player player = personService.getPlayer(playerId);
    return new PlayerEditPanel(contentId, contest, player);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
    cssFile = new PackageResourceReference(Marker.class, "div-as-table.css");
    response.render(CssHeaderItem.forReference(cssFile));
  }
}