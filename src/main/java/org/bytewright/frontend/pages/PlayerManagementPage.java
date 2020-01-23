package org.bytewright.frontend.pages;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Person;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.backend.util.PaymentStatus;
import org.bytewright.frontend.components.overview.PlayerManagementPanel;
import org.bytewright.frontend.components.template.GcmTemplate;
import org.bytewright.frontend.res.css.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated, loginMessage = "Login message", unauthorizedMessage = "Not authorized message")
public class PlayerManagementPage extends GcmTemplate {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerManagementPage.class);

  public static String getMountPath() {
    return "/players/manage";
  }

  public PlayerManagementPage(PageParameters parameters) {
    super(parameters);
  }

  @Override
  protected Component getContent(String contentId, PageParameters parameters) {
    Optional<Contest> selectedContest = GoContestManagerSession.get().getContestOpt();
    if (selectedContest.isEmpty()) {
      return new Label(contentId, "Select a contest on the homepage!");
    }
    Contest contest = selectedContest.get();
    var filterPredicate = getFilter(parameters.get("filter"), (player) -> true);
    var orderComparator = getOrder(parameters.get("order"), parameters.get("orderDir"), Comparator.comparing(Person::getSurname));
    return new PlayerManagementPanel(contentId, contest, filterPredicate, orderComparator);
  }

  private Pair<String, Comparator<Player>> getOrder(StringValue order,
      StringValue orderDir, Comparator<Player> defaultComparator) {
    Comparator<Player> playerComparator = defaultComparator;
    String orderName = order.toString("");
    if (orderName.equals("rank")) {
      playerComparator = Comparator.comparing(player -> player.getGoRank().ordinal());
    }
    if (orderName.equals("name")) {
      playerComparator = Comparator.comparing(Player::getName);
    }
    if (orderName.equals("surname")) {
      playerComparator = Comparator.comparing(Player::getSurname);
    }
    if (orderName.equals("club")) {
      playerComparator = Comparator.comparing(Player::getGoClub);
    }
    if (orderName.equals("payment")) {
      playerComparator = Comparator.comparing(Player::getPaymentStatus);
    }
    if (orderDir.toString("").equals("reverse")) {
      playerComparator = playerComparator.reversed();
    }
    return Pair.of(orderName, playerComparator);
  }

  private Pair<String, Predicate<Player>> getFilter(StringValue filterValue, Predicate<Player> defaultPredicate) {
    Predicate<Player> playerPredicate = defaultPredicate;
    String filterParam = filterValue.toString("");
    if (filterParam.equals("paid")) {
      playerPredicate = player -> player.getPaymentStatus().equals(PaymentStatus.FULLY_PAID);
    }
    if (filterParam.equals("unpaid")) {
      playerPredicate = player -> player.getPaymentStatus().equals(PaymentStatus.NOT_PAID);
    }
    return Pair.of(filterParam, playerPredicate);
  }

  @Override
  protected List<ResourceReference> getHeaderRenderContent(IHeaderResponse response) {
    return List.of(
        new PackageResourceReference(Marker.class, "style.css"),
        new PackageResourceReference(Marker.class, "div-as-table.css"));
  }
}
