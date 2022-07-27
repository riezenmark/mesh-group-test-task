package ru.meshgroup.testtask.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.meshgroup.testtask.exception.InvalidPropertyException;

@ControllerAdvice
public class InvalidPropertyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidPropertyException.class)
    protected ResponseEntity<Object> handleInvalidPropertyException(InvalidPropertyException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
