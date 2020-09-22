package de.bytewright.contestManager.backend.services;

import java.util.List;

import de.bytewright.contestManager.backend.persistence.entities.security.User;

public interface GcmUserService {
  List<User> getAllUsers();
}
