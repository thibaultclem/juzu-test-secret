/* 
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/ .
 */
package org.juzu.example.controllers;

import juzu.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.juzu.example.services.SecretService;

import javax.inject.Inject;

/**
 * Created by The eXo Platform SAS Author : Thibault Clement exo@exoplatform.com
 */
public class Admin {

  @Inject
  SecretService                    secretService;

  @Inject
  @Path("admin.gtmpl")
  org.juzu.example.templates.admin adminPage;

  @View
  @Route("/admin")
  @RequiresRoles("watcher")
  public Response adminPage(AuthorizationException e) {

    return e == null ? adminPage.with()
                                .page("admin")
                                .secretsEnabled(secretService.findAllEnabled().getSecrets())
                                .secretsDisabled(secretService.findAllDisabled().getSecrets())
                                .ok() : Response.redirect("/login");
  }

  @Action
  @Route("/admin/secrets/{id}/disable")
  @RequiresRoles("watcher")
  public Response disableSecret(Integer id, AuthorizationException e) {
    if (e == null) {
        secretService.disabled(id);
        return Admin_.adminPage();
    }
      else {
        return null;
    }
  }

  @Action
  @Route("/admin/secrets/{id}/enable")
  @RequiresRoles("watcher")
  public Response enableSecret(Integer id) {
    secretService.enabled(id);
    return Admin_.adminPage();
  }
}
