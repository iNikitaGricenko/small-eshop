package com.example.eshop.handler;

import com.example.eshop.exception.EmailExistsException;
import com.example.eshop.exception.ObjectNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> allErrors = Map.of("errors", exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(toUnmodifiableList())
        );
        return handleExceptionInternal(exception, allErrors, headers, status, request);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchObjectFound(
            ObjectNotFoundException exception, WebRequest request) {
        String bodyMessage = exception.getMessage() + "\ncheck for the current of input data";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, bodyMessage, httpHeaders, NOT_FOUND, request);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExists(
            EmailExistsException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyMessage = exception.getMessage();
        return handleExceptionInternal(exception, bodyMessage, headers, status, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleViolationAccess(ValidationException exception, WebRequest request) {
        String bodyMessage = exception.getMessage();
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(exception, bodyMessage, headers, FORBIDDEN, request);
    }

}
