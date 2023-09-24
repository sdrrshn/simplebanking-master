package com.eteration.simplebanking.exceptions;



public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient Balance");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
