package com.geographical.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String NOT_FOUND = "Node does no exist";
    private static final String BAD_REQUEST = "Could read json request body missing data";
    private static final String MALFORMED_JSON = "Could not deserialize json request body due to syntax issue or wrong data type";
    private static final String INTERNAL_SERVER_ERROR = "An unexpected error has occured";

    @ExceptionHandler(ResourceException.class)
    public final ResponseEntity<ApiError> handleResourceException(final ResourceException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, NOT_FOUND, ex.getErrors());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestPayloadException.class)
    public final ResponseEntity<ApiError> handleRequestPayloadException(final RequestPayloadException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, BAD_REQUEST, ex.getErrors());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(final Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR, Collections.emptyList());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ApiError> handleHttpMessageNotReadableException(final  HttpMessageNotReadableException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, MALFORMED_JSON, null);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
