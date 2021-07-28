package com.ekros.libraryspring.exceptions;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
