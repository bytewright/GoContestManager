package org.bytewright.backend.services;

import java.util.List;

import org.bytewright.backend.persistence.entities.security.User;

public interface GcmUserService {
  List<User> getAllUsers();
}
