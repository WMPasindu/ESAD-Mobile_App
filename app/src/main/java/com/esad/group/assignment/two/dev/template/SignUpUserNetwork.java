package com.esad.group.assignment.two.dev.template;

import android.content.Context;

import com.esad.group.assignment.two.dev.modal.request.RegisterUser;

public abstract class SignUpUserNetwork {

    public abstract void network(Context context, Object object, RegisterUser registerUser);

    protected final void doNetwork(Context context, Object object, RegisterUser registerUser) {
        network(context, object, registerUser);
    }
}
