package com.eteration.simplebanking.model;
import com.eteration.simplebanking.exceptions.AmountException;
import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {
    private static final String type="DepositTransaction";

    public DepositTransaction( double amount) {
        super( amount, type);
    }

    public DepositTransaction() {
        super(type);
    }

    @Override
    public void post(Account account) throws AmountException {
        account.credit(getAmount());

    }
}
