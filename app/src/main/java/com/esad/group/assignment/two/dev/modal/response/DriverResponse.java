package com.esad.group.assignment.two.dev.modal.response;

import java.util.ArrayList;
import java.util.List;

public class DriverResponse {

    private AccountResponse account;
    private List<Object> driverAbsences = new ArrayList<Object>();
    private Integer driverId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String nic;

    public AccountResponse getAccount() {
        return account;
    }

    public void setAccount(AccountResponse account) {
        this.account = account;
    }

    public List<Object> getDriverAbsences() {
        return driverAbsences;
    }

    public void setDriverAbsences(List<Object> driverAbsences) {
        this.driverAbsences = driverAbsences;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

}
