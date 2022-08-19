package com.example.rest_crud.exceptionhandler;

import com.example.rest_crud.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handler(CustomException exc) {

        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setData("error");
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handler(AccessDeniedException exc) {

        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setData("error");
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.FORBIDDEN.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Object>> handler(Exception exc) {
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
