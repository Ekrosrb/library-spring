package com.ekros.libraryspring.model.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
public class UserDto {
    @NotBlank(message = "{form.message.empty.name}")
    private String firstName;
    @NotBlank(message = "{form.message.empty.last.name}")
    private String lastName;
    @Email(message = "{form.message.invalid.email}")
    @NotBlank(message = "{form.message.empty.email}")
    private String email;
    @Size(min = 5, message = "{form.message.invalid.password}")
    private String password;
    @NotNull(message = "{form.message.invalid.birthday}")
    private Date birthday;
    @Size(min = 8, max = 12, message = "{form.message.invalid.phone}")
    private String phone;
}
