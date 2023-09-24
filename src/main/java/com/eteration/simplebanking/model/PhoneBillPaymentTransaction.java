package com.eteration.simplebanking.model;
import com.eteration.simplebanking.exceptions.AmountException;
import com.eteration.simplebanking.exceptions.InsufficientBalanceException;
import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends Transaction {

    private String operatorName;
    private String telNo;
    private static final String type = "PhoneBillPaymentTransaction";


    public PhoneBillPaymentTransaction(){
        super(type);
    }
    public PhoneBillPaymentTransaction(String operatorName, String telNo, double amount) {
        super(amount, type);
        this.operatorName = operatorName;
        this.telNo = telNo;
    }

    @Override
    public void post(Account account) throws AmountException, InsufficientBalanceException {
        account.debit(getAmount());
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getTelNo() {
        return telNo;
    }
}
