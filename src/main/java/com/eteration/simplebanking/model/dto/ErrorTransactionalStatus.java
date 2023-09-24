package com.eteration.simplebanking.model.dto;

public class ErrorTransactionalStatus extends TransactionStatus{

    private String message;
    public ErrorTransactionalStatus(String status) {
        super(status);
    }

    public ErrorTransactionalStatus(String status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
