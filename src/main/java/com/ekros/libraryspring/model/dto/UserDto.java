package com.ekros.libraryspring.model.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class UserDto {
    @NotBlank(message = "Enter your name!")
    private String firstName;
    @NotBlank(message = "Enter your lastname!")
    private String lastName;
    @Email(message = "Email is incorrect!")
    @NotBlank(message = "Email is empty!")
    private String email;
    @Min(value = 5, message = "Password length must be > 5")
    private String password;
    @NotNull(message = "Incorrect birthday date!")
    private Date birthday;
    @Min(value = 8, message = "Incorrect length!")
    private String phone;
}
