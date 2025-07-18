package com.sagar.PaymentRequest;


import java.math.BigDecimal;
import java.util.UUID;

import com.sagar.Enum.CardType;


public class PaymentRequest {

    private UUID transactionId;

    private BigDecimal amount;

    private String currency;

    private String merchantId;

    private CardType cardType;

    

   

	

	

	public PaymentRequest(UUID transactionId2, String merchantId2, CardType cardType2, BigDecimal amount2,
			String currency2) {
		
	}

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

  
}
