package com.bobocode.controller;

import com.bobocode.dto.ErrorDto;
import com.bobocode.exception.NoAdvisorFoundException;
import com.bobocode.exception.NoApplicationFoundException;
import org.hibernate.StaleStateException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({NoAdvisorFoundException.class, NoApplicationFoundException.class})
    public ResponseEntity<ErrorDto> handleNoUrlFoundException(Exception e) {
        var error = new ErrorDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDto> handleInvalidUrlException(IllegalStateException e) {
        var error = new ErrorDto(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(StaleStateException.class)
    public ResponseEntity<ErrorDto> handleInvalidUrlException() {
        var error = new ErrorDto("The application data is stale");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}