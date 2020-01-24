package org.bytewright.backend.persistence.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PageRepository extends JpaRepository<PageEntity, Long> {

  List<PageEntity> findAllByDisplayInNaviIsTrue();
}
