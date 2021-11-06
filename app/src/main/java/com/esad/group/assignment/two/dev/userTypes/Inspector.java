package com.esad.group.assignment.two.dev.userTypes;

import com.esad.group.assignment.two.dev.interfaces.SignUpUser;

public class Inspector implements SignUpUser {
    @Override
    public String selectedSignUpUser() {
        return "Inspector";
    }
}
