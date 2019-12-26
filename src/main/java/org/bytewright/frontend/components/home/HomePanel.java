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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.HiSayer;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.ContestService;
import org.bytewright.frontend.pages.OverviewPage;
import org.bytewright.frontend.res.css.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.lambda.components.ComponentFactory;

import java.util.List;

public class HomePanel extends Panel {
  private static final Logger LOGGER = LoggerFactory.getLogger(HomePanel.class);
  private static final long serialVersionUID = 1L;

  @SpringBean
  private HiSayer hiSayer;
  //  @SpringBean
//  private BuildProperties buildProperties;
  @SpringBean
  private ContestService contestService;

  public HomePanel(String contentId) {
    super(contentId);

    //add(new Label("AppVersion", buildProperties.getVersion()));
    add(new Label("AppVersion", 1));
    add(new Label("WicketVersion", getApplication().getFrameworkSettings().getVersion()));
    //add(new Label("SpringVersion", buildProperties.get("spring.version")));
    add(new Label("SpringVersion", 5));
    add(new Label("springBeanTest", hiSayer.sayHi()));

    add(new ContestListView(contestService.getValidContests()));
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
      var label = new Label("contestName", new PropertyModel<>(model, "name"));
      var link = new Label("contestLink", new PropertyModel<>(model, "identifier"));
      Link<Void> contestLink = ComponentFactory.link("contestLink",
        components -> toContest(new PropertyModel<>(model, "identifier")));
      item.add(label);
      item.add(contestLink);
    }

    private void toContest(PropertyModel<String> identifier) {
      PageParameters pageParameters = new PageParameters();
      pageParameters.add(OverviewPage.PATH_PARAM, identifier.toString());
      setResponsePage(OverviewPage.class, pageParameters);
    }
  }
}
