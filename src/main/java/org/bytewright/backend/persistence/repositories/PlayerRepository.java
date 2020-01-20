package org.bytewright.backend.persistence.repositories;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
