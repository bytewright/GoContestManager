package org.bytewright.frontend.pages;

import java.util.List;

import org.bytewright.frontend.pages.admin.ManageSiteUsers;
import org.bytewright.frontend.util.Mountable;

public class PageMountRegistry {
  private static List<Mountable> mountables = List.of(
      new Mountable(HomePage.getMountPath(), "Home", HomePage.class, "*:*", true),
      new Mountable(PlayerManagementPage.getMountPath(), "Player Management", PlayerManagementPage.class, "*:*", true),
      new Mountable(PlayerAddPage.getMountPath(), null, PlayerAddPage.class, "*:*", false),
      new Mountable(PlayerEditPage.getMountPath(), null, PlayerEditPage.class, "*:*", false),
      new Mountable(LoginPage.getMountPath(), "Login", LoginPage.class, "*:*", true),
      new Mountable(OverviewPage.getMountPath(), "Overview", OverviewPage.class, "*:*", true),
      new Mountable(ContestSettingsPage.getMountPath(), "Contest Settings", ContestSettingsPage.class, "*:*", true),
      new Mountable(ContestCreationPage.getMountPath(), "Create Contest", ContestCreationPage.class, "*:*", true),
      new Mountable(ManageSiteUsers.getMountPath(), "admin", ManageSiteUsers.class, "siteusers:manage", true));
  // todo sonderwertungen tabelle

  public static List<Mountable> getMountables() {
    return mountables;
  }
}
