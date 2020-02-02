package org.bytewright.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.bytewright.backend.persistence.entities.security.User;
import org.bytewright.backend.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GcmUserServiceImpl implements GcmUserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
