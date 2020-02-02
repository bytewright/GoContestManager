package org.bytewright.backend.services;

import java.util.List;

import org.bytewright.backend.persistence.entities.PageEntity;
import org.bytewright.backend.security.GoContestManagerSession;
import org.bytewright.frontend.util.Mountable;

public interface PageService {
  void createPage(Mountable mountable);

  List<PageEntity> getAllPagesForHeader(GoContestManagerSession goContestManagerSession);
}
