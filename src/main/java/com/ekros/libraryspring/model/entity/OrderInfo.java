package com.ekros.libraryspring.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private String bookName;
    private String userName;
    private String email;
    private String phone;
    private Date term;
    private Date orderDate;
    private Long fine;
    private Status status;
}
