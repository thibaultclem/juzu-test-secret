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
package org.juzu.example.services;

import org.juzu.example.models.Secret;
import org.juzu.example.models.Secrets;

import javax.inject.Singleton;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by The eXo Platform SAS Author :
 * Thibault Clement
 * exo@exoplatform.com
 */

@Singleton
public class SecretService {

  private Map<Integer, Secret> secrets;

  private Integer              counter;

  public SecretService() {
    counter = 0;
    secrets = new HashMap<Integer, Secret>();
      add("Yesterday I said I missed my PL meeting because I have to many work. In fact I was drinking free beer in Barbetta pub",
              "https://c1.staticflickr.com/3/2385/2345543856_6d0fbafb66_z.jpg?zz=1");
      add("I have a master degree but I still use Google to calculate 3*8",
              "https://yy2.staticflickr.com/7244/7245177220_3f17ee9fb8_z.jpg");
      add("I pretend to be a spy when I go out. In reality my job is to do photocopy at the embassy",
              "https://c2.staticflickr.com/2/1230/5108154392_3cc02cac67_z.jpg");
      add("I am in relathionship for 2 years. He is awesome, powerful and I never go out without him. His name is Linux",
              "http://fc02.deviantart.net/fs71/f/2009/364/9/d/christmas_love_by_skubaNiec.jpg");
      add("I spent 2 hours a day to train my cat to perform a backflip",
              "http://fc06.deviantart.net/fs15/i/2007/008/e/b/colour_cat_wallpaper_by_jellyplant.jpg");
      add("Please do not disabled my secret... !",
              "https://c1.staticflickr.com/7/6044/6327604415_4c14c406a7_z.jpg");
      disabled(6);
  }

  public boolean add(String msg, String imgUrl) {
    counter++;
    secrets.put(counter, new Secret(counter, msg, new Date(), imgUrl, true));
      return true;
  }

  public void enabled(Integer id) {
    Secret secret = secrets.get(id);
    secret.setEnable(true);
  }

  public void disabled(Integer id) {
    Secret secret = secrets.get(id);
    secret.setEnable(false);
  }

  public Secrets findAllEnabled() {
    Secrets secretsList = new Secrets();
    for (Secret secret : secrets.values()) {
      if (secret.getEnable().equals(true))
        secretsList.getSecrets().add(0, secret);
    }
    return secretsList;
  }

  public Secrets findAllDisabled() {
    Secrets secretsList = new Secrets();
    for (Secret secret : secrets.values()) {
      if (secret.getEnable().equals(false))
        secretsList.getSecrets().add(0, secret);
    }
    return secretsList;
  }

}
