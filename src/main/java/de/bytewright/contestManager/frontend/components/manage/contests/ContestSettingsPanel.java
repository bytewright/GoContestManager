package de.bytewright.contestManager.frontend.components.manage.contests;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.frontend.res.css.Marker;

public class ContestSettingsPanel extends Panel {
  public ContestSettingsPanel(String id, Contest contest) {
    super(id);
    add(new ContestCreationForm("contestEdit", Model.of(contest)));
    add(new ContestSettingsEditForm("contestSettings", Model.of(contest.getContestSettings())));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
