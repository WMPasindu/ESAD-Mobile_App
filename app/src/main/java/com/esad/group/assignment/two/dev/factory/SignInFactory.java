package com.esad.group.assignment.two.dev.factory;

import com.esad.group.assignment.two.dev.interfaces.SignUpUser;
import com.esad.group.assignment.two.dev.userTypes.Driver;
import com.esad.group.assignment.two.dev.userTypes.Inspector;
import com.esad.group.assignment.two.dev.userTypes.Passenger;

public class SignInFactory {
    //signing factory return user type
    public SignUpUser getSignInType(String selectedTokenType) {
        if (selectedTokenType == null) {
            return null;
        }
        if (selectedTokenType.equalsIgnoreCase("Passenger")) {
            return new Passenger();
        } else if (selectedTokenType.equalsIgnoreCase("Driver")) {
            return new Driver();
        } else if (selectedTokenType.equalsIgnoreCase("Inspector")) {
            return new Inspector();
        }
        return null;
    }
}
