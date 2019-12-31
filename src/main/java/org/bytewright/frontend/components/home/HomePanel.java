package org.bytewright.frontend.components.home;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.bytewright.backend.HiSayer;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.PropertiesService;
import org.bytewright.frontend.pages.OverviewPage;
import org.bytewright.frontend.res.css.Marker;
import org.wicketstuff.lambda.components.ComponentFactory;

import java.util.List;

public class HomePanel extends Panel {
  private static final long serialVersionUID = 1L;

  public HomePanel(String contentId, PropertiesService propertiesService, HiSayer hiSayer, List<Contest> validContests) {
    super(contentId, null);
    add(new Label("AppVersion", propertiesService.getAppVersion()));
    add(new Label("WicketVersion", propertiesService.getWicketVersion()));
    add(new Label("SpringVersion", propertiesService.getSpringVersion()));
    add(new Label("springBeanTest", hiSayer.sayHi()));

    add(new ContestListView(validContests));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    CssHeaderItem cssItem = CssHeaderItem.forReference(cssFile);
    response.render(cssItem);
  }

  private static class ContestListView extends ListView<Contest> {
    public ContestListView(List<Contest> validContests) {
      super("contests", validContests);
    }

    @Override
    protected void populateItem(ListItem<Contest> item) {
      IModel<Contest> model = item.getModel();
      PropertyModel<String> identifier = new PropertyModel<>(model, "uId");
      var label = new Label("contestName", new PropertyModel<>(model, "name"));
      Link<Void> contestLink = ComponentFactory.link("contestLink",
        components -> toContest(identifier.getObject()));
      item.add(label);
      item.add(contestLink);
    }

    private void toContest(String identifier) {
      PageParameters pageParameters = new PageParameters();
      pageParameters.set(0, identifier); // todo get correct identifier
      setResponsePage(OverviewPage.class, pageParameters);
    }
  }
}
