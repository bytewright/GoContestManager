package org.bytewright.backend.persistance.repositories;

import java.util.Optional;

import org.bytewright.backend.persistance.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findById(long id);
}
