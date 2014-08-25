/*
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.juzu.example.security;

import juzu.impl.common.Tools;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SecretRealm extends AuthorizingRealm {

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) getAvailablePrincipal(principals);
    if ("admin@secret.com".equals(username)) {
      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      info.setRoles(Tools.set("watcher"));
      info.setStringPermissions(Tools.set("remove"));
      return info;
    } else if ("tclement@exoplatform.com".equals(username)) {
      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      info.setRoles(Tools.set("watcher"));
      return info;
    }
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String principal = (String) token.getPrincipal();
    String credentials = new String((char[]) token.getCredentials());
    if ("admin@secret.com".equals(principal) && "secret".equals(credentials)) {
      return new SimpleAuthenticationInfo(principal, credentials.toCharArray(), getName());
    } else if ("tclement@exoplatform.com".equals(principal) && "pass".equals(credentials)) {
      return new SimpleAuthenticationInfo(principal, credentials.toCharArray(), getName());
    }
    return null;
  }
}
