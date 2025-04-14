package com.restapi_example1.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomeException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> custom(Exception e, WebRequest r) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                new Date(System.currentTimeMillis()),
                r.getDescription(true)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
