package com.esad.group.assignment.two.dev.modal.response;

import java.util.ArrayList;
import java.util.List;

public class RouteResponse {

    private Integer amount;
    private List<Object> buses = new ArrayList<Object>();
    private String destination;
    private String origin;
    private String routeId;
    private List<TicketResponse> timeTables = new ArrayList<TicketResponse>();

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Object> getBuses() {
        return buses;
    }

    public void setBuses(List<Object> buses) {
        this.buses = buses;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List<TicketResponse> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TicketResponse> timeTables) {
        this.timeTables = timeTables;
    }
}
