package de.bytewright.contestManager.backend.persistence.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bytewright.contestManager.backend.persistence.entities.security.Permission;

@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
  Optional<Permission> findByPermEquals(String perm);

}
