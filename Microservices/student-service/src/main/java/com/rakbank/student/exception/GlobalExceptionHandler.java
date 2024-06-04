package com.rakbank.student.exception;

import com.rakbank.student.exception.custom.EntityNotFoundException;
import com.rakbank.student.exception.custom.IdMissingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorList = new ArrayList<>();
        for (ConstraintViolation<?> errors : ex.getConstraintViolations()) {
            errorList.add(errors.getMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(errorList, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdMissingException.class)
    public ResponseEntity<ErrorResponse> handleIdMissingException(IdMissingException ex) {
        List<String> errorList = new ArrayList<>();
        errorList.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorList, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        List<String> errorList = new ArrayList<>();
        errorList.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorList, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



}
