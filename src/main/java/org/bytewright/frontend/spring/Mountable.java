package org.bytewright.frontend.spring;

import org.apache.wicket.Page;

public class Mountable {
  private String path;
  private Class<? extends Page> clazz;

  private Mountable(String path, Class<? extends Page> clazz) {
    this.path = path;
    this.clazz = clazz;
  }

  public static Mountable of(String path, Class<? extends Page> clazz) {
    return new Mountable(path, clazz);
  }

  public String getMountPath() {
    return path;
  }

  public Class<? extends Page> getPageClass() {
    return clazz;
  }

  @Override
  public String toString() {
    return "Mountable{" +
        "path='" + path + '\'' +
        ", clazz=" + clazz +
        '}';
  }
}
