package com.esad.group.assignment.two.dev.modal.response;

import java.util.ArrayList;
import java.util.List;

public class PassengerResponse {

    private AccountResponse accountResponse;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String nic;
    private Integer passengerId;
    private List<Object> smartCards = new ArrayList<Object>();

    public AccountResponse getAccount() {
        return accountResponse;
    }

    public void setAccount(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public List<Object> getSmartCards() {
        return smartCards;
    }

    public void setSmartCards(List<Object> smartCards) {
        this.smartCards = smartCards;
    }
}
