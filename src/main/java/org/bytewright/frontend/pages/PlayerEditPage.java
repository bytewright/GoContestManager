package org.bytewright.frontend.pages;

import java.util.List;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.backend.services.PersonService;
import org.bytewright.frontend.components.manage.players.PlayerEditPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class PlayerEditPage extends GcmTemplate {
  public static final String PLAYER_PARAM = "playerId";

  @SpringBean
  private PersonService personService;

  public PlayerEditPage(PageParameters parameters) {
    super(parameters);
  }

  public static String getMountPath() {
    return "/players/edit";
  }

  @Override
  protected Component getContent(String contentId, PageParameters pageParameters) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    Long playerId = pageParameters.get(PLAYER_PARAM).to(Long.class);
    Player player = personService.getPlayer(playerId);
    return new PlayerEditPanel(contentId, contest, player);
  }

  @Override
  protected List<ResourceReference> getHeaderRenderContent(IHeaderResponse response) {
    return List.of(
        new PackageResourceReference(Marker.class, "style.css"),
        new PackageResourceReference(Marker.class, "div-as-table.css"));
  }
}
