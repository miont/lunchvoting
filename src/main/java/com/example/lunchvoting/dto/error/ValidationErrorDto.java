package com.example.lunchvoting.dto.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation error info sending to client
 * https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/
 */
public class ValidationErrorDto {

    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public ValidationErrorDto() {
    }

    public void addFieldError(String path, String message) {
        fieldErrors.add(new FieldErrorDto(path, message));
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}
