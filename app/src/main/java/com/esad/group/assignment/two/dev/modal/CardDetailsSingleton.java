package com.esad.group.assignment.two.dev.modal;

public class CardDetailsSingleton {
    private static CardDetailsSingleton INSTANCE = null;

    private long transactionId;
    private String cardHolderName;
    private String CreditCardNumber;
    private String expiryDate;
    private String ccv;
    private String creditAmount;

    private CardDetailsSingleton() {
    }

    public CardDetailsSingleton(long transactionId, String cardHolderName, String creditCardNumber, String expiryDate, String ccv, String creditAmount) {
        this.transactionId = transactionId;
        this.cardHolderName = cardHolderName;
        CreditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
        this.creditAmount = creditAmount;
    }

    public static CardDetailsSingleton getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(CardDetailsSingleton INSTANCE) {
        CardDetailsSingleton.INSTANCE = INSTANCE;
    }

    public static CardDetailsSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CardDetailsSingleton();
        }
        return (INSTANCE);
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }
}
