package org.project.stock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class WebControllerHandlerException {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(NoSuchElementException e) {
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String otherExceptionInApplication(Exception e) {
        return "Произошла ошибка!";
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String countProductExceptionHandler(ArithmeticException e) {
        return e.getMessage();
    }
}
