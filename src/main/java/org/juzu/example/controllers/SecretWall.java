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
import org.juzu.example.models.Secret;
import org.juzu.example.services.SecretService;

import javax.inject.Inject;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * exo@exoplatform.com
 */
public class SecretWall {

    @Inject
    SecretService secretService;

    @Inject
    @Path("secretWall.gtmpl")
    org.juzu.example.templates.secretWall secretWall;

    @View
    @Route("/secretWall")
    public Response.Content secretWall() {

        System.out.println("start secretWall");
        for(Secret secret : secretService.findAllEnabled().getSecrets()) {
            System.out.println(secret.getMessage());
        }
        return secretWall.with().page("secretWall").secrets(secretService.findAllEnabled().getSecrets()).ok();
    }
}

