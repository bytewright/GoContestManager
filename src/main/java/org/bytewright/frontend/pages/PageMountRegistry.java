package org.bytewright.frontend.pages;

import java.util.Set;

import org.bytewright.frontend.spring.Mountable;
import org.springframework.stereotype.Component;

@Component
public class PageMountRegistry {
  private static Set<Mountable> mountables = Set.of(
      Mountable.of(OverviewPage.getMountPath(), OverviewPage.class));

  public Set<Mountable> getMountables() {
    return mountables;
  }
}
