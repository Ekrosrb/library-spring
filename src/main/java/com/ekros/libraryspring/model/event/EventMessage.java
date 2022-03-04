package com.ekros.libraryspring.model.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventMessage {

  private String transactionId;
  private String time;
  private EventData data;

}
