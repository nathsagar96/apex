package com.apex.exceptions;

import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
    ErrorResponse error =
        new ErrorResponse(
            "CLIENT_ERROR",
            ex.getMessage(),
            Instant.now(),
            Map.of("status", String.valueOf(ex.getStatusCode().value())));
    return ResponseEntity.status(ex.getStatusCode()).body(error);
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleHttpServerErrorException(HttpServerErrorException ex) {
    ErrorResponse error =
        new ErrorResponse(
            "SERVER_ERROR",
            ex.getMessage(),
            Instant.now(),
            Map.of("status", String.valueOf(ex.getStatusCode().value())));
    return ResponseEntity.status(ex.getStatusCode()).body(error);
  }

  @ExceptionHandler(ResourceAccessException.class)
  public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex) {
    ErrorResponse error =
        new ErrorResponse(
            "SERVICE_UNAVAILABLE",
            "External service is unavailable or unreachable: " + ex.getMessage(),
            Instant.now());
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse error =
        new ErrorResponse(
            "INTERNAL_SERVER_ERROR",
            "An unexpected error occurred: " + ex.getMessage(),
            Instant.now());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
