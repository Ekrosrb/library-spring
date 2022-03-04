package com.ekros.libraryspring.model.event;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookData {

  private String name;
  private String year;
  private String author;
  private List<String> genres;

}
