package com.ekros.libraryspring.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class BookDto {
    @NotBlank(message = "Book name is empty!")
    private String name;
    @NotBlank(message = "Indicate the author!")
    private String author;
    @NotBlank(message = "Indicate the edition!")
    private String edition;
    private String description;
    private String descriptionRu;
    @Min(value = 0, message = "Must be >= 0")
    private int count;
}
