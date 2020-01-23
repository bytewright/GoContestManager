package org.bytewright.backend.services;

import java.util.List;

import org.bytewright.backend.persistence.entities.security.User;
import org.bytewright.backend.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GcmUserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
