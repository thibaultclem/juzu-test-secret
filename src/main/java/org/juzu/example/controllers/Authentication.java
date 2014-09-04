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
import juzu.Action;
import juzu.plugin.shiro.Logout;
import org.apache.shiro.authc.AuthenticationException;
import org.juzu.example.services.AlertService;

import javax.inject.Inject;

/**
 * Created by The eXo Platform SAS Author : Tclement exo@exoplatform.com
 */
public class Authentication {

  /*
   * Service
   */
  @Inject
  AlertService                     alertService;

  /*
   * Template
   */

  @Inject
  @Path("login.gtmpl")
  org.juzu.example.templates.login loginPage;

  /*
   * View
   */

  @View
  @Route("/login")
  public Response.Content login() {

    return loginPage.with().page("admin").ok();
  }

  @View
  @Route("/loginFailed")
  public Response.Content loginFailed() {

    return loginPage.with()
                    .page("admin")
                    .alertMessage(alertService.getBootstrapAlertMessage("error",
                                                                        "Authentication Failed",
                                                                        "Wrong email or password"))
                    .ok();
  }

  /*
   * Action
   */

  @Action
  @Route("/authentication/login")
  @juzu.plugin.shiro.Login(username = "email", password = "pwd", rememberMe = "remember")
  public Response doLogin(String email, String pwd, String remember, AuthenticationException e) {

    return e == null ? Response.redirect("/admin") : Authentication_.loginFailed();
  }

  @Action
  @Route("/authentication/logout")
  @Logout
  public Response doLogout() {

    return Response.redirect("/login");
  }
}
