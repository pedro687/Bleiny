package com.bleiny.communities.application.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends RuntimeException {

    public String formattedString(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + " = " + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(","));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = formattedString(ex.getFieldErrors());
        ExceptionErrorDTO exceptionErrorDTO = ExceptionErrorDTO.builder()
                .message(errorMessage)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity
                .badRequest()
                .body(exceptionErrorDTO);
    }
}
