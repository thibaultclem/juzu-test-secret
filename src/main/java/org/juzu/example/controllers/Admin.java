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

import juzu.Path;
import juzu.Response;
import juzu.Route;
import juzu.View;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;

import javax.inject.Inject;

/**
 * Created by The eXo Platform SAS Author : Thibault Clement exo@exoplatform.com
 */
public class Admin {

  @Inject
  @Path("admin.gtmpl")
  org.juzu.example.templates.admin adminPage;

  @View
  @Route("/admin")
  @RequiresRoles("watcher")
  public Response admin(AuthorizationException e) {

    return e == null ? adminPage.with().page("admin").ok() : Response.redirect("/login");
  }
}
