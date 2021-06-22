package com.ekros.libraryspring.model.dto;

import lombok.Data;

@Data
public class BookDto {
    private String name;
    private String author;
    private String edition;
    private String description;
    private String descriptionRu;
    private int count;
}
