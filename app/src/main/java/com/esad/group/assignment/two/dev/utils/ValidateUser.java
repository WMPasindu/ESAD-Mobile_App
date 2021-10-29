package com.esad.group.assignment.two.dev.utils;


import com.esad.group.assignment.two.dev.modal.LoginUser;

public class ValidateUser implements com.esad.group.assignment.two.dev.interfaces.ValidateUser {
    @Override
    public boolean validateUser(LoginUser loginUser) {
        //Username
        if (loginUser.getUsername() == null || loginUser.getUsername().trim().length() == 0) {
            return false;
        }
        //Password
        if (loginUser.getPassword() == null || loginUser.getPassword().trim().length() == 0) {
            return false;
        }
        return true;
    }
}
