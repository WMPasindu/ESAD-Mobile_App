package com.esad.group.assignment.two.dev.modal;

public class LeaveHistory {
    private final String type;
    private final String fromDate;
    private final String toDate;

    public LeaveHistory(String type, String fromDate, String toDate) {
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
