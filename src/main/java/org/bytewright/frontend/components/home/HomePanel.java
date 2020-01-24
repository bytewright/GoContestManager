package org.bytewright.frontend.components.home;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.persistence.dtos.Contest;
import org.bytewright.backend.persistence.dtos.ContestSettings;
import org.bytewright.backend.services.PropertiesService;
import org.bytewright.frontend.res.css.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePanel extends Panel {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomePanel.class);
  private static final long serialVersionUID = 1L;
  @SpringBean
  private PropertiesService propertiesService;

  public HomePanel(String contentId, List<Contest> validContests, Optional<Contest> selectedContest) {
    super(contentId, null);
    add(new Label("AppVersion", propertiesService.getAppVersion()));
    add(new Label("WicketVersion", propertiesService.getWicketVersion()));
    add(new Label("SpringVersion", propertiesService.getSpringVersion()));
    add(new ContestListView(validContests, selectedContest.orElse(null), "contestsTable"));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    response.render(CssHeaderItem.forReference(cssFile));
    cssFile = new PackageResourceReference(Marker.class, "div-as-table.css");
    response.render(CssHeaderItem.forReference(cssFile));
  }

  private static class ContestListView extends ListView<Contest> {
    private final Contest selectedContest;

    ContestListView(List<Contest> validContests, @Nullable Contest selectedContest, String contests) {
      super(contests, validContests);
      this.selectedContest = selectedContest;
    }

    @Override
    protected void populateItem(ListItem<Contest> item) {
      Contest model = item.getModel().getObject();
      ContestSettings settings = model.getContestSettings();
      Form<String> selection = new ContestSelectionForm("contestSelection", LambdaModel.of(model::getUniqueId));
      var nameLabel = new Label("contestName", LambdaModel.of(settings::getName));
      var startLabel = new Label("contestStart", LambdaModel.of(settings::getDateStart));
      var endLabel = new Label("contestEnd", LambdaModel.of(settings::getDateEnd));
      if (selectedContest != null && selectedContest.equals(model)) {
        nameLabel.add(new SetSelectedBehavior());
      }
      item.add(nameLabel);
      item.add(startLabel);
      item.add(endLabel);
      item.add(selection);
    }

    static class SetSelectedBehavior extends Behavior {
      @Override
      public void onComponentTag(Component component, ComponentTag tag) {
        tag.put("class", "selectedContest");
      }
    }
  }
}
