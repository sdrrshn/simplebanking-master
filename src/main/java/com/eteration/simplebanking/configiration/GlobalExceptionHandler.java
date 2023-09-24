package com.eteration.simplebanking.configiration;

import com.eteration.simplebanking.model.dto.ErrorTransactionalStatus;
import com.eteration.simplebanking.model.dto.TransactionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TransactionStatus> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorTransactionalStatus("ERROR",ex.getMessage()));
    }
}
