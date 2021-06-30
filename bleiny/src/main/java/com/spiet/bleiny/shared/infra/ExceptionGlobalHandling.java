package com.spiet.bleiny.shared.infra;

import com.spiet.bleiny.shared.infra.utils.dto.FormErrorDto;
import org.flywaydb.core.api.android.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandling {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FormErrorDto> formErrorDtos = new ArrayList<>();
        var fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            formErrorDtos.add(new FormErrorDto(err.getField(), message));
        });

        return formErrorDtos;
    }
}
