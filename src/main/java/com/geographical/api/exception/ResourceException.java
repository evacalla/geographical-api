package com.geographical.api.exception;

import java.util.List;

public class ResourceException extends RuntimeException {

    List<String> errors;

    public ResourceException(List<String> errors) {
        this.errors = errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
