package com.esad.group.assignment.two.dev.modal.request;

import com.google.gson.annotations.SerializedName;

public class RegisterUser {

    @SerializedName("account")
    public UserAccount account;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("mobileNo")
    public String mobileNo;

    @SerializedName("nic")
    public String nic;

    public RegisterUser() {
    }

    private RegisterUser(final RegisterUserBuilder builder) {
        account = builder.account;
        firstName = builder.firstName;
        lastName = builder.lastName;
        mobileNo = builder.mobileNo;
        nic = builder.nic;
    }

    public static class RegisterUserBuilder {
        public UserAccount account;
        public String firstName;
        public String lastName;
        public String mobileNo;
        public String nic;

        public RegisterUserBuilder(UserAccount account, String firstName, String lastName, String mobileNo, String nic) {
            this.account = account;
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobileNo = mobileNo;
            this.nic = nic;
        }

        public UserAccount getUsername() {
            return account;
        }

        public RegisterUserBuilder setAccount(final UserAccount account) {
            this.account = account;
            return this;
        }

        public RegisterUserBuilder setFName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public RegisterUserBuilder setLName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public RegisterUserBuilder setMobileNo(final String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public RegisterUserBuilder setNIC(final String nic) {
            this.nic = nic;
            return this;
        }

        public RegisterUser create() {
            return new RegisterUser(this);
        }
    }
}
