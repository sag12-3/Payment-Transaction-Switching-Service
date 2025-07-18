package com.sagar.ResponseDto;


import java.util.UUID;

public class PaymentResponse {
    private UUID transactionId;
    private String status;  
    private String message;

   
    public PaymentResponse() {}
    
    public PaymentResponse(UUID transactionId, String status, String message) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
    }

 
    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

