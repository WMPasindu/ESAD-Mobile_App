package com.esad.group.assignment.two.dev.modal.response;

import java.util.ArrayList;
import java.util.List;

public class ReservationResponse {
    private PassengerResponse passenger;
    private String resDate;
    private Integer resId;
    private String reservationType;
    private List<Object> tickets = new ArrayList<Object>();

    public PassengerResponse getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerResponse passenger) {
        this.passenger = passenger;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public List<Object> getTickets() {
        return tickets;
    }

    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }
}
