package com.example.eshop.exception.handler;

import com.example.eshop.exception.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ObjectNotFoundHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchObjectFoundException (
            ObjectNotFoundException exception, WebRequest request
    ) {
        String bodyMessage = exception.getMessage() + "\ncheck for the current of input datas";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, bodyMessage, httpHeaders, HttpStatus.NOT_FOUND, request);
    }

}
