package edu.uniformix.api.exceptions;

import org.hibernate.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Centralized exception handler for server-side exceptions.
 */
@ControllerAdvice
public class ServerSideExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServerSideExceptionHandler.class);

    /**
     * Handles DataAccessException indicating a data access error.
     * Returns an HTTP 500 (Internal Server Error) with an explanatory message.
     * @param ex The DataAccessException captured during data access operation.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        logger.error("Data access exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data access exception: " + ex.getMessage());
    }

    /**
     * Handles TransactionException indicating a transaction error.
     * Returns an HTTP 500 (Internal Server Error) with an explanatory message.
     * @param ex The TransactionException captured during transaction operation.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleTransactionException(TransactionException ex) {
        logger.error("Transaction exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transaction exception: " + ex.getMessage());
    }

    /**
     * Handles ConcurrencyFailureException indicating a concurrency error.
     * Returns an HTTP 500 (Internal Server Error) with an explanatory message.
     * @param ex The ConcurrencyFailureException captured during concurrency operation.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleConcurrencyException(ConcurrencyFailureException ex) {
        logger.error("Concurrency exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Concurrency exception: " + ex.getMessage());
    }

    /**
     * Handles IOException indicating an I/O error.
     * Returns an HTTP 500 (Internal Server Error) with an explanatory message.
     * @param ex The IOException captured during I/O operation.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleIOException(IOException ex) {
        logger.error("I/O error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An I/O error occurred: " + ex.getMessage());
    }

    /**
     * Handles generic exceptions indicating internal server errors.
     * Returns an HTTP 500 (Internal Server Error) with an explanatory message.
     * @param ex The Exception captured for any other internal server error scenario.
     * @return ResponseEntity with HTTP 500 and the error message.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleServerSideExceptions(Exception ex) {
        logger.error("Internal server error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occurred: " + ex.getMessage());
    }
}
