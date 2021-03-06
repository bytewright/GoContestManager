package de.bytewright.contestManager.backend.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.entities.BaseAuditedEntity;
import de.bytewright.contestManager.backend.persistence.entities.PageEntity;
import de.bytewright.contestManager.backend.persistence.entities.security.Permission;
import de.bytewright.contestManager.backend.persistence.repositories.PageRepository;
import de.bytewright.contestManager.backend.persistence.repositories.PermissionRepository;
import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.frontend.util.Mountable;

@Component
@Transactional
public class PageServiceImpl implements PageService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);

  @Autowired
  private PageRepository pageRepository;
  @Autowired
  private PermissionRepository permissionRepository;

  @Override
  public void createPage(Mountable mountable) {
    PageEntity pageEntity = new PageEntity();
    pageEntity.setPageUrlPath(mountable.getMountPath());
    pageEntity.setPageClass(mountable.getPageClass());
    pageEntity.setAnchorName(mountable.getaName());
    pageEntity.setDisplayInNavi(mountable.isDisplayInNavi());
    Permission permission = getOrCreatePermission(mountable.getRequiredPermission());
    pageEntity.setRequiredPermission(permission);
    pageRepository.save(pageEntity);
  }

  @Override
  public List<PageEntity> getAllPagesForHeader(GoContestManagerSession goContestManagerSession) {
    List<PageEntity> entityList = pageRepository.findAllByDisplayInNaviIsTrue();
    entityList.sort(Comparator.comparing(BaseAuditedEntity::getId));
    // todo check perms
    LOGGER.debug("User {} can see {} links in navi", goContestManagerSession.getUserDbId(), entityList.size());
    return entityList;
  }

  private Permission getOrCreatePermission(String requiredPermission) {
    Optional<Permission> permissionOptional = permissionRepository.findByPermEquals(requiredPermission);
    return permissionOptional.orElseGet(() -> permissionRepository.saveAndFlush(new Permission(requiredPermission)));
  }
}
