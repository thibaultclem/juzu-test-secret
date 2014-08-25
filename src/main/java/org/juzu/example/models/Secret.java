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
package org.juzu.example.models;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by The eXo Platform SAS Author : Thibault Clement exo@exoplatform.com
 */
@Named("secret")
public class Secret implements Serializable {

  private Integer id;

  private String  message;

  private Date    createdDate;

  private String  imageUrl;

  private Boolean enable;

  public Secret(Integer id, String message, Date createdDate, String imageUrl, Boolean enable) {
    this.id = id;
    this.message = message;
    this.createdDate = createdDate;
    this.imageUrl = imageUrl;
    this.enable = enable;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }
}
