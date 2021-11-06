package com.esad.group.assignment.two.dev.network;

import androidx.annotation.IntDef;

import com.esad.group.assignment.two.dev.interfaces.Api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ServiceLocator {
    // Declare the constants
    public static final int GSON = 0;
    public static final int PLAIN_TEXT = 1;
    public static final int LOGIN = 2;
    private static ServiceLocator INSTANCE;
    private final Api apiEndpointInterfacePlainText;
    private final Api apiEndpointInterface;
    private final Api apiEndpointInterfaceLogin;

    private ServiceLocator() {
        apiEndpointInterfacePlainText = RetrofitBuilder.getRetrofitInstancePlainText().create(Api.class);
        apiEndpointInterface = RetrofitBuilder.getRetrofitInstance().create(Api.class);
        apiEndpointInterfaceLogin = RetrofitBuilder.getRetrofitInstanceLogin().create(Api.class);
    }

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }

    public Api getApi(@Type int type) {
        switch (type) {
            case PLAIN_TEXT:
                return apiEndpointInterfacePlainText;
            case GSON:
                return apiEndpointInterface;
            case LOGIN:
                return apiEndpointInterfaceLogin;
            default:
                return null;

        }
    }
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GSON, PLAIN_TEXT, LOGIN})
    public @interface Type {
    }
}
