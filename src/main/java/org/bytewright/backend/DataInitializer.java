package org.bytewright.backend;

import java.util.List;

import org.bytewright.backend.persistence.entities.ContestEntity;
import org.bytewright.backend.persistence.repositories.ContestRepository;
import org.bytewright.backend.services.PageService;
import org.bytewright.backend.util.DataImporter;
import org.bytewright.frontend.pages.PageMountRegistry;
import org.bytewright.frontend.util.Mountable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);
  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private PageService pageService;
  @Autowired
  private DataImporter dataImporter;

  @EventListener
  @Async
  public void onContextRefreshedEvent(ContextRefreshedEvent event) {
    createPages();
    List<ContestEntity> entityList = dataImporter.getContestsFromFile("import.json");
    contestRepository.saveAll(entityList);
  }

  private void createPages() {
    for (Mountable mountable : PageMountRegistry.getMountables()) {
      pageService.createPage(mountable);
    }
  }

}
