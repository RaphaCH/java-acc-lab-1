package com.javalab.java_lab.controller.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import com.javalab.java_lab.mapper.ErrorMessageMapper;
import com.javalab.java_lab.model.CustomException;
import com.javalab.java_lab.model.ErrorConstant;
import com.javalab.java_lab.model.ErrorMessage;
import com.javalab.java_lab.model.Response;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // ErrorMessage for the moment - by design - only accepts strings, is this how we would use the enums with it?
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ErrorConstant.TEC001.key, ErrorConstant.TEC001.value, "some details should go here too", errors);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> handleCustomException(CustomException exception) {
        ErrorMessage errorMessage = ErrorMessageMapper.toErrorMessage(exception);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(errorMessage.getStatusCode()));
        return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
    }
}

