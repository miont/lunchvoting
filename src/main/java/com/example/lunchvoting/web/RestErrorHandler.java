package com.example.lunchvoting.web;

import com.example.lunchvoting.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exception handler class. Logs exceptions and converts them to the appropriate HTTP status codes
 */
@ControllerAdvice(annotations = RestController.class)
public class RestErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestErrorHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(NotFoundException ex) {
        log.debug("handle NotFoundException");
    }

}
