package com.ekros.libraryspring.model.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {

  private int age;
  private String country;

}
