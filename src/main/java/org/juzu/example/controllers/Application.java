/*
 * Copyright 2013 eXo Platform SAS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.juzu.example.controllers;

import juzu.*;
import org.juzu.example.services.AlertService;
import org.juzu.example.services.SecretService;

import javax.inject.Inject;
import java.io.IOException;

public class Application {

  /*
   * Services
   */
  @Inject
  SecretService                        secretService;

  @Inject
  AlertService                         alertService;

  /*
   * Template
   */

  @Inject
  @Path("index.gtmpl")
  org.juzu.example.templates.index     index;

  @Inject
  @Path("addSecret.gtmpl")
  org.juzu.example.templates.addSecret addSecret;

  /*
   * View
   */

  @View()
  @Route("/")
  public Response.Content index() throws IOException {

    return index.with().page("index").ok();
  }

  @View()
  @Route("/shareSecret")
  public Response.Content shareSecretView() {

    return addSecret.with().page("shareSecretView").ok();
  }

  @View()
  @Route("/shareSecretAddSuccess")
  public Response.Content shareSecretAddSuccessView() {

    return addSecret.with()
                    .page("shareSecretView")
                    .alertMessage(alertService.getBootstrapAlertMessage("success",
                                                                        "Congratulation",
                                                                        "Your secret has been added on the Secret Wall"))
                    .ok();
  }

  @View()
  @Route("/shareSecretAddFailed")
  public Response.Content shareSecretAddFailedView() {

    return addSecret.with()
                    .page("shareSecretView")
                    .alertMessage(alertService.getBootstrapAlertMessage("error",
                                                                        "Message not added",
                                                                        "Please try again or contact us if error persist"))
                    .ok();
  }

  /*
   * Actions
   */
  @Action
  @Route("shareSecret/add")
  public Response addSecret(String msg, String imgURL) {
    if (secretService.add(msg, imgURL)) {
      return Application_.shareSecretAddSuccessView();
    } else {
      return Application_.shareSecretAddFailedView();
    }
  }

}
