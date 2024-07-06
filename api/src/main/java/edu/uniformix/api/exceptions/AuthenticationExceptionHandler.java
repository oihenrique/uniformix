package edu.uniformix.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Centralized exception handler for authentication-related exceptions.
 */
@ControllerAdvice
public class AuthenticationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

    /**
     * Handles AuthenticationException indicating authentication failure.
     * Returns an HTTP 401 (Unauthorized) with an explanatory message.
     * @param ex The AuthenticationException captured during authentication.
     * @return ResponseEntity with HTTP 401 and the error message.
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        logger.error("Authentication exception occurred", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication exception: " + ex.getMessage());
    }

    /**
     * Handles AccessDeniedException indicating access denied due to insufficient permissions.
     * Returns an HTTP 403 (Forbidden) with an explanatory message.
     * @param ex The AccessDeniedException captured during authorization.
     * @return ResponseEntity with HTTP 403 and the error message.
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("Access denied exception occurred", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied exception: " + ex.getMessage());
    }
}
