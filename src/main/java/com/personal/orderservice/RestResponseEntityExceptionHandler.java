package com.personal.orderservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = { ConstraintViolationException.class })
  public ResponseEntity<?> handleConstraintValidations(ConstraintViolationException e) {
    Map<Path, String> messages = e.getConstraintViolations().stream().collect(
      Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage)
    );
    return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
  }
 }
