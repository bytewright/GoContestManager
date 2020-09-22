package de.bytewright.contestManager.backend.persistence.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bytewright.contestManager.backend.persistence.entities.security.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(long id);

  Optional<User> findByUsername(String userName);
}
