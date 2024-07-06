package edu.uniformix.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centralized exception handler for all API requests, providing standardized error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles generic exceptions that are not caught by specific handlers.
     * Returns an HTTP 500 (Internal Server Error) with a detailed error message.
     * @param ex The exception captured during request processing.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericExceptions(Exception ex) {
        logger.error("An internal error occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal error occurred: " + ex.getMessage());
    }

    /**
     * Handles ResourceNotFoundException indicating a requested resource was not found.
     * Returns an HTTP 404 (Not Found) with an explanatory message.
     * @param ex The ResourceNotFoundException captured during request processing.
     * @return ResponseEntity with HTTP 404 and the error message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Resource not found: " + ex.getMessage());
    }
}
