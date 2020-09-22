package de.bytewright.contestManager.backend.init;

import java.util.List;

import org.springframework.core.Ordered;

import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;

public interface DataImporter extends Ordered {

  List<ContestEntity> importContests();

  @Override
  default int getOrder() {
    return 0;
  }
}
