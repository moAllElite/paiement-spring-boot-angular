package com.example.backend.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
@AllArgsConstructor
public class HandlerExceptions extends RuntimeException {
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ErrorHandler handleException(EntityNotFoundException e) {
        return  ErrorHandler.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({EntityExistsException.class})
    public @ResponseBody ErrorHandler handleException(EntityExistsException e) {
        return ErrorHandler.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
