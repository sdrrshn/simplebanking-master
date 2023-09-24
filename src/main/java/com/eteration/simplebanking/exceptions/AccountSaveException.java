package com.eteration.simplebanking.exceptions;

import org.springframework.dao.DataAccessException;

public class AccountSaveException extends RuntimeException {



    public AccountSaveException(String message, DataAccessException ex) {
    super(message,ex);
    }
}
