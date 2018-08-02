package com.tkarropoulos.jwtdemo.utils;

public class CustomLoggerMessages {

    private String loggerMessage;

    protected CustomLoggerMessages() {
    }

    public CustomLoggerMessages(String loggerMessage) {
        this.loggerMessage = loggerMessage;
    }

    public String getLoggerMessage() {
        return loggerMessage;
    }
}
