package com.geographical.api.exception;

import java.util.List;

public class RequestPayloadException extends RuntimeException {

    List<String> errors;

    public RequestPayloadException(List<String> errors) {
        this.errors = errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
