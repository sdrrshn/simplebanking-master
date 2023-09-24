package com.eteration.simplebanking.exceptions;

public class AmountException extends IllegalArgumentException {
    public AmountException() {
        super("Invalid Amount");
    }


    public AmountException(String message) {
        super(message);
    }

}
