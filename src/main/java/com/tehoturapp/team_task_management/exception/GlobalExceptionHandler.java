package com.tehoturapp.team_task_management.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles exceptions related to entities not found (user, role, task list, task).
     * Combines multiple exception types into a single method.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity containing error response with HTTP status 404 (Not Found).
     */
    @ExceptionHandler({
            UserNotFoundException.class,
            RoleNotFoundException.class,
            TaskListNotFoundException.class,
            TaskNotFoundException.class,
            TaskNotInTaskListException.class
    })
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(RuntimeException ex){

        log.error("Not Found Exception: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any unexpected exceptions not specifically caught by other handlers.
     *
     * @param ex The exception that was thrown.
     * @return ResponseEntity containing error response with HTTP stavem 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){

        log.error("Unexpected error", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An error occurred",
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * Handles validation errors with incorrectly entered data in the request.
     * E.g. when the field does not pass annotations like @NotNull, @Size, @Email, etc.
     *
     * @param ex Exception containing details about validation errors.
     * @param headers HTTP headers (automatically provided by Spring).
     * @param status HTTP status codes.
     * @param request An object representing an HTTP request.
     * @return ResponseEntity containing error response with list of validation errors.
     */
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        log.warn("Validation failed: {}", errorMessages);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                request.getDescription(false),
                "Validation errors: " + errorMessages);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}