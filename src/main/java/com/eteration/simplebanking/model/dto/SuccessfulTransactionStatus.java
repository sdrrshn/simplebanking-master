package com.eteration.simplebanking.model.dto;



public class SuccessfulTransactionStatus extends TransactionStatus {

    public SuccessfulTransactionStatus(String approvalCode) {
        super("OK",approvalCode);
    }

    public SuccessfulTransactionStatus(String status, String approvalCode) {
        super(status, approvalCode);
    }
}
