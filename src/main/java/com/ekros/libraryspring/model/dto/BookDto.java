package com.ekros.libraryspring.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class BookDto {
    @NotBlank(message = "{form.message.empty.book.name}")
    private String name;
    @NotBlank(message = "{form.message.empty.book.author}")
    private String author;
    @NotBlank(message = "{form.message.empty.edition}")
    private String edition;
    @NotBlank(message = "{form.message.empty.year}")
    private String year;
    @NotBlank(message = "{form.message.empty.genres}")
    private String genres;
    private String description;
    private String descriptionRu;
    @Min(value = 0, message = "{form.message.invalid.count}")
    private int count;
}
