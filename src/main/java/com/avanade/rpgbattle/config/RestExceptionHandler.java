package com.avanade.rpgbattle.config;

import com.avanade.rpgbattle.exception.ErrorMessage;
import com.avanade.rpgbattle.exception.InvalidInputException;
import com.avanade.rpgbattle.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity< ErrorMessage > handle( Exception ex, HttpStatus statusCode, String description ) {
        var message = new ErrorMessage( statusCode, LocalDateTime.now( ), ex.getMessage( ), description, null );
        ex.printStackTrace( );
        return new ResponseEntity<>( message, message.getStatusCode( ) );
    }

    private ResponseEntity< ErrorMessage > handle( Exception ex, HttpStatus statusCode, String description, Map<String, String> validations ) {
        var message = new ErrorMessage( statusCode, LocalDateTime.now( ), "Action Required: Fix the validations", description, validations );
        ex.printStackTrace( );
        return new ResponseEntity<>( message, message.getStatusCode( ) );
    }

    @ExceptionHandler( { InvalidInputException.class } )
    public ResponseEntity< ErrorMessage > invalidInputHandler( Exception ex, WebRequest request ) {
        return handle( ex, HttpStatus.BAD_REQUEST, request.getDescription( false ) );
    }

    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity< ErrorMessage > resourceNotFoundHandler( Exception ex, WebRequest request ) {
        return handle( ex, HttpStatus.NOT_FOUND, request.getDescription( false ) );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity< ErrorMessage > validationHandler(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> validations = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validations.put(fieldName, errorMessage);
        });

        return handle( ex, HttpStatus.BAD_REQUEST, request.getDescription( false ), validations );
    }
}