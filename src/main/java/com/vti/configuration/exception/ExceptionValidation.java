package com.vti.configuration.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Để bắt Reques Validate
public class ExceptionValidation extends ResponseEntityExceptionHandler {
    @Override
    // hàm bắt lỗi Validate
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for(ObjectError error : exception.getBindingResult().getAllErrors()) {
            String filed = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(filed, message);

        }
        return new ResponseEntity<>(errors, status);
    }
}
