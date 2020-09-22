package de.bytewright.contestManager.backend.security;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.bytewright.contestManager.backend.persistence.entities.security.Permission;
import de.bytewright.contestManager.backend.persistence.entities.security.Role;
import de.bytewright.contestManager.backend.persistence.entities.security.User;
import de.bytewright.contestManager.backend.persistence.repositories.UserRepository;

public class ShiroJpaRepoRealm extends AuthorizingRealm {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShiroJpaRepoRealm.class);

  private static final String REALM_NAME = "GCM-SHIRO-REALM";
  private static final String REALM_SALT = "GCM-pepper+salt";
  @Autowired
  private UserRepository userRepository;

  public ShiroJpaRepoRealm() {
    super(null, null); // todo?
    setName("ShiroJpaRepoRealm");
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    String userName = (String) principalCollection.getPrimaryPrincipal();
    Optional<User> userOpt = userRepository.findByUsername(userName);
    if (userOpt.isEmpty()) {
      return null;
    }
    User user = userOpt.get();

    Set<Permission> permissionSet = user.getRoles().stream()
        .map(Role::getPermissions)
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    permissionSet.addAll(user.getPermissions());
    Set<String> permNames = permissionSet.stream().map(Permission::getPerm).collect(Collectors.toSet());
    Set<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    LOGGER.debug("User {} has {} permissions: {}", user.getUsername(), permissionSet.size(), permissionSet);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setRoles(roleNames);
    info.setStringPermissions(permNames);

    if (roleNames.contains("admin")) {
      LOGGER.debug("Admin '{}' ({} {}, id {}) logged in at: {}", user.getUsername(), user.getId(), user.getFirstName(),
          user.getLastName(),
          Instant.now());
    }

    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
    if (!(authToken instanceof UsernamePasswordToken))
      throw new IllegalStateException("Token must be an instance of UsernamePasswordToken");

    UsernamePasswordToken token = (UsernamePasswordToken) authToken;
    Optional<User> userOpt = userRepository.findByUsername(token.getUsername());
    if (userOpt.isEmpty()) {
      return null;
    }
    User user = userOpt.get();
    GoContestManagerSession.get().setUserDbId(user.getId());

    SimpleAccount simpleAccount = new SimpleAccount(
        user.getUsername(),
        user.getPassword(),
        ByteSource.Util.bytes(REALM_SALT),
        REALM_NAME
    );
    LOGGER.debug("User {} logged in: {}", user.getUsername(), simpleAccount);
    return simpleAccount;
  }

}