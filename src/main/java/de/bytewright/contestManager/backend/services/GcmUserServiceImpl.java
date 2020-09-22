package de.bytewright.contestManager.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bytewright.contestManager.backend.persistence.entities.security.User;
import de.bytewright.contestManager.backend.persistence.repositories.UserRepository;

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
