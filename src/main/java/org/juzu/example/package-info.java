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

@juzu.Application(defaultController = Application.class)
@Servlet(value = "/")
@Bindings({
        @Binding(org.juzu.example.security.SecretRealm.class),
        @Binding(org.juzu.example.services.SecretService.class)
})
@WebJars({
        @WebJar("bootstrap"),
        @WebJar("jquery")
})
@Scripts({
        @Script(id = "bootstrap.js", value = "bootstrap/3.2.0/js/bootstrap.min.js", depends = "jquery.js"),
        @Script(id = "jquery.js", value = "jquery/2.1.1/jquery.min.js")
})
@Stylesheets({
        @Stylesheet(id = "bootstrap.css", value = "bootstrap/3.2.0/css/bootstrap.min.css"),
        @Stylesheet(id = "my.css", value = "css/my.css"),
        @Stylesheet(id = "Aller", value = "http://fonts.googleapis.com/css?family=Aller", location = AssetLocation.URL)
})
@Assets("*")
@Shiro(config = @juzu.plugin.shiro.Configuration("test/security/shiro.ini"), realms = {@Realm(value = org.juzu.example.security.SecretRealm.class, name = "secret")}, rememberMe = true)
package org.juzu.example;

import juzu.asset.AssetLocation;
import juzu.plugin.asset.*;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.servlet.Servlet;
import juzu.plugin.shiro.Realm;
import juzu.plugin.shiro.Shiro;
import juzu.plugin.webjars.WebJar;
import juzu.plugin.webjars.WebJars;
import org.juzu.example.controllers.Application;