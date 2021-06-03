package com.vyasmeet.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {
    // timestamp - when it happened
    private Date timestamp;

    // message - exception message
    private String message;

    // Details - exception details
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
