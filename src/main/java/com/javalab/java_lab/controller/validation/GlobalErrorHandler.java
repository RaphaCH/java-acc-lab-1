package com.javalab.java_lab.controller.validation;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.javalab.java_lab.mapper.ErrorMessageMapper;
import com.javalab.java_lab.model.CustomException;
import com.javalab.java_lab.model.ErrorConstant;
import com.javalab.java_lab.model.ErrorMessage;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String stringOfErrors = exception.getBindingResult().getAllErrors().stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining("; "));
        ErrorMessage message = new ErrorMessage(
            ErrorConstant.TEC001.key,
            ErrorConstant.TEC001.value, 
            exception.getBody().getDetail() ,
            stringOfErrors);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> handleCustomException(CustomException exception) {
        ErrorMessage errorMessage = ErrorMessageMapper.toErrorMessage(exception);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(errorMessage.getStatusCode()));
        return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
    }
}

