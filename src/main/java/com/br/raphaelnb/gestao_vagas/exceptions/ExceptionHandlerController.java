package com.br.raphaelnb.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<ErrorMessageDTO> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
            errors.add(error);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
