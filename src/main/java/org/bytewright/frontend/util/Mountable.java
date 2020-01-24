package org.bytewright.frontend.util;

import org.apache.wicket.Page;

public class Mountable {
  private String path;
  private String aName;
  private Class<? extends Page> clazz;
  private String requiredPermission;
  private boolean displayInNavi;

  public Mountable(String path, String aName, Class<? extends Page> clazz, String requiredPermission,
      boolean displayInNavi) {
    this.path = path;
    this.aName = aName;
    this.clazz = clazz;
    this.requiredPermission = requiredPermission;
    this.displayInNavi = displayInNavi;
  }

  public String getaName() {
    return aName;
  }

  public static Mountable of(String path, Class<? extends Page> clazz) {
    return of(path, clazz, "*:*", false);
  }

  public static Mountable of(String path, Class<? extends Page> clazz, String requiredPermission, boolean displayInNavi) {
    return new Mountable(path, null, clazz, requiredPermission, displayInNavi);
  }

  public String getMountPath() {
    return path;
  }

  public Class<? extends Page> getPageClass() {
    return clazz;
  }

  public String getRequiredPermission() {
    return requiredPermission;
  }

  public boolean isDisplayInNavi() {
    return displayInNavi;
  }

  @Override
  public String toString() {
    return "Mountable{" +
        "path='" + path + '\'' +
        ", clazz=" + clazz +
        '}';
  }
}
