package com.tkarropoulos.jwtdemo.utils;

public class CustomLoggerMessages {

    private String errorMessage;

    protected CustomLoggerMessages() {
    }

    public CustomLoggerMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
