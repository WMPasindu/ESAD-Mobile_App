package com.esad.group.assignment.two.dev.modal;

public class TokenHistory {
    private final int id;
    private final String date;
    private final String busNumber;
    private final String status;

    public TokenHistory(int id, String date, String busNumber, String status) {
        this.id = id;
        this.date = date;
        this.busNumber = busNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getStatus() {
        return status;
    }
}
