package org.bytewright.backend.persistence.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(long id);
}
