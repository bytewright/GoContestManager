package org.bytewright.backend.persistence.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
  Optional<Permission> findByPermEquals(String perm);

}
