package com.java.quiz.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.quiz.payloads.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleGenericException(Exception exception, HttpServletRequest httpServletRequest) {

        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>("ERROR", "SOMETHING_WENT_WRONG", Collections.emptyMap());

        apiResponse.setPath(httpServletRequest.getRequestURI());

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fr : fieldErrors) {
            errors.put(fr.getField(), fr.getDefaultMessage());
        }

        ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", "VALIDATION_FAILED", errors);

        response.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        ApiResponse<Map<String, String>> response = new ApiResponse<>("ERRORS", ex.getMessage(), Collections.emptyMap());

        response.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest httpServletRequest) {
        ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", ex.getMessage(), Collections.emptyMap());
        response.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest httpServletRequest) {

        ApiResponse<Map<String, String>> response = new ApiResponse<>("ERROR", ex.getMessage(), Collections.emptyMap());
        response.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }


}