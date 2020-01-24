package org.bytewright.backend.persistence.repositories;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
}
