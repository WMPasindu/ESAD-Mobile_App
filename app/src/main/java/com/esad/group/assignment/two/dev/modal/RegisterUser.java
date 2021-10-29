package com.esad.group.assignment.two.dev.modal;

public class RegisterUser {
    public String username;
    public String email;
    public String password;
    public String displayName;
    public String phoneNumber;
    public String accountType;

    private RegisterUser(final RegisterUserBuilder builder) {
        username = builder.username;
        email = builder.email;
        password = builder.password;
        displayName = builder.displayName;
        phoneNumber = builder.phoneNumber;
        accountType = builder.accountType;
    }

    public String getUsername() {
        return username;
    }

    public static class RegisterUserBuilder {
        public String username;
        public String email;
        public String password;
        public String displayName;
        public String phoneNumber;
        public String accountType;

        public RegisterUserBuilder(String username, String email, String password, String displayName, String phoneNumber, String accountType) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.displayName = displayName;
            this.phoneNumber = phoneNumber;
            this.accountType = accountType;
        }

        public RegisterUserBuilder setUsername(final String username) {
            this.username = username;
            return this;
        }

        public RegisterUserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public RegisterUserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public RegisterUserBuilder setDisplayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }

        public RegisterUserBuilder setPhoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public RegisterUserBuilder setAccountType(final String accountType) {
            this.accountType = accountType;
            return this;
        }

        public RegisterUser create() {
            return new RegisterUser(this);
        }
    }
}
