package com.ekros.libraryspring.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthday;
    private String phone;
}
