package com.crm.Exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice()
public class HandleException{
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(ResourceNotFound e, WebRequest request){
        ErrorDetails error = new ErrorDetails(e.getMessage(), new Date(), request.getDescription(false));
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(Exception e, WebRequest request){
        ErrorDetails error = new ErrorDetails(e.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
