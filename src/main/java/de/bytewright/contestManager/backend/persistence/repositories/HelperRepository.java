package de.bytewright.contestManager.backend.persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bytewright.contestManager.backend.persistence.entities.HelperEntity;

@Transactional
public interface HelperRepository extends JpaRepository<HelperEntity, Long> {
}
