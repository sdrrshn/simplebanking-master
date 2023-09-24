package com.eteration.simplebanking.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(){
        super("Account Not Found");
    }
    public AccountNotFoundException(String message) {
        super(message);
    }
}
