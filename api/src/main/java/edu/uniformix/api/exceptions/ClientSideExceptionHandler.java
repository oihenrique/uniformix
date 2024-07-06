package edu.uniformix.api.exceptions;

import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Centralized exception handler for client-side exceptions.
 */
@ControllerAdvice
public class ClientSideExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClientSideExceptionHandler.class);

    /**
     * Handles MethodArgumentTypeMismatchException indicating method argument type mismatch.
     * Returns an HTTP 400 (Bad Request) with an explanatory message.
     * @param ex The MethodArgumentTypeMismatchException captured during method argument validation.
     * @return ResponseEntity with HTTP 400 and the error message.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        logger.error("MethodArgumentTypeMismatchException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Method argument type mismatch: " + ex.getMessage());
    }

    /**
     * Handles InvalidDataAccessApiUsageException indicating invalid usage of data access API.
     * Returns an HTTP 400 (Bad Request) with an explanatory message.
     * @param ex The InvalidDataAccessApiUsageException captured during data access operation.
     * @return ResponseEntity with HTTP 400 and the error message.
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDataAccessApiUsage(InvalidDataAccessApiUsageException ex) {
        logger.error("InvalidDataAccessApiUsageException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Invalid data access API usage: " + ex.getMessage());
    }

    /**
     * Handles BindException indicating binding or validation errors.
     * Returns an HTTP 400 (Bad Request) with an explanatory message.
     * @param ex The BindException captured during data binding or validation.
     * @return ResponseEntity with HTTP 400 and the error message.
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBindException(BindException ex) {
        logger.error("BindException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Bind exception: " + ex.getMessage());
    }

    /**
     * Handles ValidationException indicating validation errors.
     * Returns an HTTP 400 (Bad Request) with an explanatory message.
     * @param ex The ValidationException captured during data validation.
     * @return ResponseEntity with HTTP 400 and the error message.
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        logger.error("ValidationException occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Validation exception: " + ex.getMessage());
    }

    /**
     * Handles generic exceptions indicating bad requests.
     * Returns an HTTP 400 (Bad Request) with an explanatory message.
     * @param ex The Exception captured for any other bad request scenario.
     * @return ResponseEntity with HTTP 400 and the error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestExceptions(Exception ex) {
        logger.error("Bad request exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
    }
}
