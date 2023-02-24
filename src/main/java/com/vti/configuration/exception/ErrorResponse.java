package com.vti.configuration.exception;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ErrorResponse {

    private String timestamp = LocalDateTime.now().toString();
    private int code;
    private String message;
    private Object errors;
    private String moreInformation;
    private String detailMessage;

    public ErrorResponse(int code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.moreInformation = "localhost:8080/api/v1/exceptions/" + code;
    }

    public ErrorResponse(int code, String message, String detailMessage, Object errors) {
        this(code, message, detailMessage);
        this.errors = errors;
    }
}
