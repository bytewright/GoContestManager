package de.bytewright.contestManager.frontend.components.admin;

import java.util.List;
import java.util.stream.Collectors;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bytewright.contestManager.backend.persistence.entities.security.Permission;
import de.bytewright.contestManager.backend.persistence.entities.security.Role;
import de.bytewright.contestManager.backend.persistence.entities.security.User;
import de.bytewright.contestManager.backend.services.GcmUserService;
import de.bytewright.contestManager.frontend.res.css.Marker;

public class AdminUserPanel extends Panel {
  private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserPanel.class);

  @SpringBean
  private GcmUserService userService;

  public AdminUserPanel(String contentId) {
    super(contentId);
    List<User> allUsers = userService.getAllUsers();
    add(new UsersListView(allUsers, "userList"));
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    PackageResourceReference cssFile = new PackageResourceReference(Marker.class, "style.css");
    response.render(CssHeaderItem.forReference(cssFile));
    cssFile = new PackageResourceReference(Marker.class, "div-as-table.css");
    response.render(CssHeaderItem.forReference(cssFile));
  }

  private static class UsersListView extends ListView<User> {
    public UsersListView(List<User> allUsers, String contentId) {
      super(contentId, allUsers);
    }

    @Override
    protected void populateItem(ListItem<User> item) {
      User user = item.getModelObject();
      var nameLabel = new Label("userName", LambdaModel.of(user::getUsername));
      var surnameLabel = new Label("userSurname", LambdaModel.of(user::getLastName));
      var rolesLabel = new Label("userRoles", LambdaModel.of(() -> user.getRoles().stream()
          .map(Role::getName)
          .collect(Collectors.joining())));
      var permsLabel = new Label("userPerms",
          LambdaModel.of(() -> user.getPermissions().stream().map(Permission::getPerm).collect(Collectors.joining())));
      Form<String> selection = new Form<>("userSelection", LambdaModel.of(() -> Long.toString(user.getId()))) {
        @Override
        protected void onSubmit() {
          LOGGER.info("Form submitted. user: {}", getModelObject());
        }
      };
      item.add(nameLabel);
      item.add(surnameLabel);
      item.add(rolesLabel);
      item.add(permsLabel);
      item.add(selection);
    }
  }
}
