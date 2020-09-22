package de.bytewright.contestManager.backend.services;

import java.util.List;

import de.bytewright.contestManager.backend.persistence.entities.PageEntity;
import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.frontend.util.Mountable;

public interface PageService {
  void createPage(Mountable mountable);

  List<PageEntity> getAllPagesForHeader(GoContestManagerSession goContestManagerSession);
}
