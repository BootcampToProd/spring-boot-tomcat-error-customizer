package com.bootcamptoprod.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalErrorController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorController.class);

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException noHandlerFoundException, WebRequest request) {
        logger.error("No handler found exception encountered");
        return new ResponseEntity<>(new ErrorResponse(404, "Resource not found"), HttpStatus.NOT_FOUND);
    }

}