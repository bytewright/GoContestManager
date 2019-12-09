package org.bytewright.backend.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * https://github.com/ceefour/paxwicket-shiro/blob/master/app/src/main/java/com/hendyirawan/paxwicketshiro/app/ShiroRealm.java
 */
public class ShiroRealm extends AuthorizingRealm {

  public ShiroRealm() {
    setName("testrealm");
    CredentialsMatcher cm = new SimpleCredentialsMatcher();
    setCredentialsMatcher(cm);
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    String userName = (String) principalCollection.getPrimaryPrincipal();

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    if ("bush".equals(userName)) {
      info.addRole("president");
      info.addStringPermission("war:start");
      info.addStringPermission("war:watch");
    } else if ("ban ki-moon".equals(userName)) {
      info.addRole("nato");
      info.addStringPermission("war:*");
    } else if ("balkenende".equals(userName)) {
      info.addRole("primeminister");
      info.addStringPermission("war:watch");
    }

    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

    String userName = (String) authToken.getPrincipal();
    if ("bush".equals(userName)) {
      return new SimpleAuthenticationInfo("bush", "president", getName());
    } else if ("ban ki-moon".equals(userName)) {
      return new SimpleAuthenticationInfo("ban ki-moon", "nato", getName());
    } else if ("balkenende".equals(userName)) {
      return new SimpleAuthenticationInfo("balkenende", "h.potter", getName());
    }

    throw new AuthenticationException();

  }

}