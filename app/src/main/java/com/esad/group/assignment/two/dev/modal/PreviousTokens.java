package com.esad.group.assignment.two.dev.modal;

public class PreviousTokens {
    private final String id;
    private final String date;
    private final String amount;

    public PreviousTokens(String id, String date, String amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
