package com.stepstone.training.arena.controller;

import com.stepstone.training.arena.controller.ArenaApiError;
import com.stepstone.training.arena.service.model.NoSuchTournamentException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        ArenaApiError arenaApiError = new ArenaApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(arenaApiError, arenaApiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Failed JSON binding";
        ArenaApiError arenaApiError = new ArenaApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(arenaApiError, arenaApiError.getStatus());
    }

    @ExceptionHandler(NoSuchTournamentException.class)
    protected ResponseEntity<Object> handleNoSuchTournamentException(
            NoSuchTournamentException ex) {
        ArenaApiError arenaApiError = new ArenaApiError(HttpStatus.NOT_FOUND, "No such tournament: " + ex.getId(), ex.getMessage());
        return new ResponseEntity<>(arenaApiError, arenaApiError.getStatus());
    }
}