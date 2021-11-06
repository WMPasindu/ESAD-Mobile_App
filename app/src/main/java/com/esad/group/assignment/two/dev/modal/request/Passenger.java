package com.esad.group.assignment.two.dev.modal.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

    @SerializedName("passengerId")
    @Expose
    private int passengerId;

    public void setPassengerId(int passengerId){
        this.passengerId = passengerId;
    }
    public int getPassengerId(){
        return this.passengerId;
    }
}
