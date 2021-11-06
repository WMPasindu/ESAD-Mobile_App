package com.esad.group.assignment.two.dev.modal.response;

public class VerifyTicketResponse {
    private int ticketId;
    private String origin;
    private String destination;
    private String travellingDateTime;
    private int seatNo;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTravellingDateTime() {
        return travellingDateTime;
    }

    public void setTravellingDateTime(String travellingDateTime) {
        this.travellingDateTime = travellingDateTime;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
}
