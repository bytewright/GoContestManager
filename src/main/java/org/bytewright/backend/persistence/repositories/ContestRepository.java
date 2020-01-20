package org.bytewright.backend.persistence.repositories;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
  Optional<ContestEntity> findByShortIdentifier(String uniqueId);

  Optional<ContestEntity> findFirstByStartUtcTimeIsAfter(Instant now);
}
