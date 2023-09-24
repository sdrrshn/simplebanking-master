package com.eteration.simplebanking.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionStatus {
    private String status;



    private String approvalCode;



    public TransactionStatus(String status) {
        this.status = status;
    }


    public TransactionStatus(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    public String getStatus() {
        return status.toString();
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getApprovalCode() {
        return approvalCode;
    }
}
