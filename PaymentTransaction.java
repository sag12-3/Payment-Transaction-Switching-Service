package com.sagar.ResponseEntity;


import com.sagar.Enum.CardType;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class PaymentTransaction {

    @Id
    private UUID transactionId;

    private BigDecimal amount;

    private String currency;

    private String merchantId;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private String processorUsed;  

    private String routeTo;       

    private String status;         

    private String message;       

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getProcessorUsed() {
        return processorUsed;
    }

    public void setProcessorUsed(String processorUsed) {
        this.processorUsed = processorUsed;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(String routeTo) {
        this.routeTo = routeTo;
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

   

    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", cardType=" + cardType +
                ", processorUsed='" + processorUsed + '\'' +
                ", routeTo='" + routeTo + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

	
}

