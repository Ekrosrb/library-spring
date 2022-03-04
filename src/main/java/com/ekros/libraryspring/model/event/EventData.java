package com.ekros.libraryspring.model.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventData {

  private BookData book;
  private UserData user;

}
