package com.kendirita.tour_user_service.exception;


import com.kendirita.tour_user_service.response.ResponseHandler;
import com.kendirita.tour_user_service.util.TimestampUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseHandler.generateResponse(
                UUID.randomUUID(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null,
                TimestampUtil.now()
        );
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        return ResponseHandler.generateResponse(
                UUID.randomUUID(),
                ex.getMessage(),
                HttpStatus.CONFLICT,
                null,
                TimestampUtil.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseHandler.generateResponse(
                UUID.randomUUID(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null,
                TimestampUtil.now()
        );
    }
}