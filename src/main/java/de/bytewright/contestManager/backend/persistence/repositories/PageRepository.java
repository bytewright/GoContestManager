package de.bytewright.contestManager.backend.persistence.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bytewright.contestManager.backend.persistence.entities.PageEntity;

@Transactional
public interface PageRepository extends JpaRepository<PageEntity, Long> {

  List<PageEntity> findAllByDisplayInNaviIsTrue();
}
