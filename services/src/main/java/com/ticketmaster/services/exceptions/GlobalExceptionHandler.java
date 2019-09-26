package com.ticketmaster.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<ErrorResponse> handleException(Exception exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                status.value(),
                exception.getMessage() + ", " + HttpStatus.NOT_FOUND,
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, status);

    }
}
