package org.blueliner.orderservice.exception;

import org.blueliner.orderservice.exception.type.ClientNotFoundException;
import org.blueliner.orderservice.exception.type.InvalidStatusException;
import org.blueliner.orderservice.exception.type.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OrderNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(OrderNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ClientNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(ClientNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidStatusException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(InvalidStatusException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
