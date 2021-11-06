package com.esad.group.assignment.two.dev.template.userTypes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.esad.group.assignment.two.dev.modal.request.RegisterUser;
import com.esad.group.assignment.two.dev.modal.response.RegisterUserResponse;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.template.SignUpUserNetwork;
import com.esad.group.assignment.two.dev.utils.AppConstants;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Passenger extends SignUpUserNetwork {
    // passenger registration
    @Override
    public void network(Context context, Object object, RegisterUser registerUser) {
        ServiceLocator.getInstance().getApi(ServiceLocator.GSON).createUser(registerUser).enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("LOG", "Sucess : " + response.body().toString());
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(AppConstants.PASSENGER_SP, response.body().getPassengerId());
                        editor.commit();
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Congratulations...").setContentText("You have been successfully registered").show();
                    } else {
                        Log.d("LOG", "1st Error : " + response.errorBody().toString());
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("LOG", "2nd Error : ");
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                }
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                Log.d("LOG", "3rd Error : ");
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
            }
        });
    }
}