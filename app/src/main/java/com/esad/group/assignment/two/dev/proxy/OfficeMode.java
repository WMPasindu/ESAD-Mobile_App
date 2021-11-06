package com.esad.group.assignment.two.dev.proxy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.esad.group.assignment.two.dev.interfaces.InternetAccess;

public class OfficeMode implements InternetAccess {
    /* proxy pattern -> checking device online or offline
    * if offline return false
    */
    @Override
    public boolean grantInternetAccess(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }
}
