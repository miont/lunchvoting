package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.error.ValidationErrorDto;
import com.example.lunchvoting.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Exception handler class. Logs exceptions and converts them to the appropriate HTTP status codes
 */
@ControllerAdvice(annotations = RestController.class)
public class RestErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestErrorHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFoundException(NotFoundException ex) {
        log.debug("handle NotFoundException");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationErrorDto processValidationErrors(MethodArgumentNotValidException ex) {
        log.debug("handle MethodArgumentNotValidException");
        BindingResult result = ex.getBindingResult();
        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        for(FieldError fieldError : result.getFieldErrors()) {
            validationErrorDto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return validationErrorDto;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public void processDatabaseConflicts(DataIntegrityViolationException e) {  // TODO: probably shoul return json
        log.debug("handle conflict error");
    }

}
