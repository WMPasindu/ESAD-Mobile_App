package com.esad.group.assignment.two.dev.modal.request;

import com.google.gson.annotations.SerializedName;

public class UserAccount {
    @SerializedName("email")
    private String email;

    public UserAccount() {
    }

    public UserAccount(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
