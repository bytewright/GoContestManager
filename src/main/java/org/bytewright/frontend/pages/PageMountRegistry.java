package org.bytewright.frontend.pages;

import java.util.Set;

import org.bytewright.frontend.util.Mountable;
import org.springframework.stereotype.Component;

@Component
public class PageMountRegistry {
  private static Set<Mountable> mountables = Set.of(
      Mountable.of(HomePage.getMountPath(), HomePage.class),
      Mountable.of(PlayerManagementPage.getMountPath(), PlayerManagementPage.class),
      Mountable.of(PlayerAddPage.getMountPath(), PlayerAddPage.class),
      Mountable.of(PlayerEditPage.getMountPath(), PlayerEditPage.class),
      Mountable.of(LoginPage.getMountPath(), LoginPage.class),
      Mountable.of(OverviewPage.getMountPath(), OverviewPage.class),
      Mountable.of(ContestSettingsPage.getMountPath(), ContestSettingsPage.class),
      Mountable.of(ContestCreationPage.getMountPath(), ContestCreationPage.class));
  // todo sonderwertungen tabelle

  public Set<Mountable> getMountables() {
    return mountables;
  }
}
