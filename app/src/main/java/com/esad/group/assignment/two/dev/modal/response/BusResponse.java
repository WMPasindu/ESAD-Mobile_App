package com.esad.group.assignment.two.dev.modal.response;

import java.util.ArrayList;
import java.util.List;

public class BusResponse {
    private String busNo;
    private Integer maxSeat;
    private RouteResponse route;
    private List<Object> tickets = new ArrayList<Object>();
    public String getBusNo() {
        return busNo;
    }
    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }
    public Integer getMaxSeat() {
        return maxSeat;
    }
    public void setMaxSeat(Integer maxSeat) {
        this.maxSeat = maxSeat;
    }
    public RouteResponse getRoute() {
        return route;
    }
    public void setRoute(RouteResponse route) {
        this.route = route;
    }
    public List<Object> getTickets() {
        return tickets;
    }
    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }
}
