package de.bytewright.contestManager.backend.init;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;

/**
 * Responsible for populating the db with contests at startup.
 */
@Service
public class ContestImporterImpl implements ContestImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestImporterImpl.class);
  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private Set<DataImporter> dataImporter;

  @Override
  @Transactional
  public void importContests() {
    LOGGER.info("Found {} data sources for contests, starting import", dataImporter.size());
    for (DataImporter importer : dataImporter) {
      List<ContestEntity> entityList = importer.importContests();
      LOGGER.info("Data importer {} provided {} contest entities", importer.getClass().getSimpleName(), entityList.size());
      contestRepository.saveAll(entityList);
    }
  }
}
