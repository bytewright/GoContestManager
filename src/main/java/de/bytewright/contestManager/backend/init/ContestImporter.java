package de.bytewright.contestManager.backend.init;

import javax.transaction.Transactional;

/**
 * For transaction proxy
 */
public interface ContestImporter {
  @Transactional
  void importContests();
}
