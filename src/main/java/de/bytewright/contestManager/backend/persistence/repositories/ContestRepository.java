package de.bytewright.contestManager.backend.persistence.repositories;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;

@Transactional
public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
  Optional<ContestEntity> findByShortIdentifier(String uniqueId);

  Optional<ContestEntity> findFirstByStartUtcTimeIsAfter(Instant now);
}
