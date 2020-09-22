package de.bytewright.contestManager.frontend.components.manage.contests;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.frontend.res.css.Marker;

public class ContestCreationPanel extends Panel {
  public ContestCreationPanel(String contentId, PageParameters parameters) {
    super(contentId);

    IModel<String> successMsg = LambdaModel.of(() -> "");
    if (parameters != null && !parameters.isEmpty()) {
      boolean success = parameters.get("success").toBoolean();
      successMsg = LambdaModel.of(() -> success ? "Contest created successfully!" : "Contest could not be created");
    }
    Form<Contest> selection = new ContestCreationForm("contestCreation");
    add(new Label("creationSuccess", successMsg));
    add(selection);
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }
}
