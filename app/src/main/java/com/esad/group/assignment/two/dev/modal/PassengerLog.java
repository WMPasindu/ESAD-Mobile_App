package com.esad.group.assignment.two.dev.modal;

public class PassengerLog {
    private final String id;
    private final String date;
    private final String departure;
    private final String arrival;

    public PassengerLog(String id, String date, String departure, String arrival) {
        this.id = id;
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }
}
