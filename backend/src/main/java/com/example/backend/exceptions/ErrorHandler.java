package com.example.backend.exceptions;

import lombok.Builder;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorHandler (
        String message,
        HttpStatus status,
        LocalDateTime timestamp
){ }
