package org.bytewright.backend.persistence.repositories;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {
}
