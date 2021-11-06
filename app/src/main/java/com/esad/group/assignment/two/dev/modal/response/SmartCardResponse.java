package com.esad.group.assignment.two.dev.modal.response;

public class SmartCardResponse {
    private String activated;
    private Integer cardId;
    private Integer credits;
    private PassengerResponse passengerResponse;
    private String validityPeriod;

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public PassengerResponse getPassenger() {
        return passengerResponse;
    }

    public void setPassenger(PassengerResponse passengerResponse) {
        this.passengerResponse = passengerResponse;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}
