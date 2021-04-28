package com.geographical.api.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private HttpStatus code;
    private String message;
    private List<String> errors;

    public ApiError(final HttpStatus code, final String message, final List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
