package com.esad.group.assignment.two.dev.modal.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SmartCard {

    @SerializedName("activated")
    @Expose
    private String activated;

    @SerializedName("credits")
    @Expose
    private int credits;

    @SerializedName("passenger")
    @Expose
    private Passenger passenger;

    @SerializedName("validityPeriod")
    @Expose
    private String validityPeriod;


    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getActivated() {
        return this.activated;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return this.credits;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getValidityPeriod() {
        return this.validityPeriod;
    }
}
