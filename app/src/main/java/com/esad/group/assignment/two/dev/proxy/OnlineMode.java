package com.esad.group.assignment.two.dev.proxy;

import android.content.Context;
import android.util.Log;

import com.esad.group.assignment.two.dev.interfaces.InternetAccess;

public class OnlineMode implements InternetAccess {
    // proxy online object which returns offline object -- proxy pattern
    // adding more secure and avoid creating lots of memory using objects
    @Override
    public boolean grantInternetAccess(Context context) {
        OfficeMode officeMode = new OfficeMode();
        return officeMode.grantInternetAccess(context);
    }
}
