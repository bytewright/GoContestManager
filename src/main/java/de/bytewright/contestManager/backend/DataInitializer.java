package de.bytewright.contestManager.backend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;
import de.bytewright.contestManager.backend.services.PageService;
import de.bytewright.contestManager.backend.util.DataImporter;
import de.bytewright.contestManager.frontend.pages.PageMountRegistry;
import de.bytewright.contestManager.frontend.util.Mountable;

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
