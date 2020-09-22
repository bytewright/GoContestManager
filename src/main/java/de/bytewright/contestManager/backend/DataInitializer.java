package de.bytewright.contestManager.backend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.init.ContestImporter;
import de.bytewright.contestManager.backend.services.PageService;
import de.bytewright.contestManager.frontend.pages.PageMountRegistry;
import de.bytewright.contestManager.frontend.util.Mountable;

@Component
public class DataInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

  @Autowired
  private PageService pageService;
  @Autowired
  private ContestImporter contestImporter;

  @EventListener
  @Async
  public void onContextRefreshedEvent(ContextRefreshedEvent event) {
    LOGGER.info("AppContext is refreshed, starting to populate pages and contests: {}", event);
    createPages();
    contestImporter.importContests();
  }

  private void createPages() {
    List<Mountable> mountables = PageMountRegistry.getMountables();
    LOGGER.info("Found {} mountable Pages for wicket", mountables.size());
    for (Mountable mountable : mountables) {
      pageService.createPage(mountable);
    }
  }
}
