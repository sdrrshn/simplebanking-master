package com.eteration.simplebanking.model;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;

import javax.persistence.Entity;
@Entity
public class WithdrawalTransaction extends Transaction {
    private static final String type = "WithdrawalTransaction";

    public WithdrawalTransaction() {
        super(type);
    }
    public WithdrawalTransaction( double amount ) {
        super(amount,type);
    }

    @Override
    public void post(Account account) throws InsufficientBalanceException {
        account.debit(getAmount());
    }
}


