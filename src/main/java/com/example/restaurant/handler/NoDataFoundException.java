package com.example.restaurant.handler;

import lombok.Data;

@Data
public class NoDataFoundException extends RuntimeException{
    private String message;

    public NoDataFoundException(String message) {
        this.message = message;
    }
}
