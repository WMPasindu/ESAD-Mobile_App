package com.esad.group.assignment.two.dev;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.esad.group.assignment.two.dev.interfaces.InternetAccess;
import com.esad.group.assignment.two.dev.modal.LoginUser;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.navigation.NavigateUserScreen;
import com.esad.group.assignment.two.dev.proxy.OfficeMode;
import com.esad.group.assignment.two.dev.proxy.OnlineMode;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.esad.group.assignment.two.dev.utils.ValidateUser;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.et_email)
    TextInputEditText email;
    @BindView(R.id.et_password)
    TextInputEditText password;
    private LoginUser loginUser;
    private ValidateUser validateUser;
    @BindView(R.id.sp_type_spinner)
    Spinner sp_type_spinner;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // adding data to dropdwon
        sp_type_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select Type");
        categories.add("Passenger");
        categories.add("Driver");
        categories.add("Inspector");
        categories.add("Transport Manager");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_spinner.setAdapter(dataAdapter);

        init();
    }

    private void init() {
        loginUser = new LoginUser();
        validateUser = new ValidateUser();
    }
    // navigate button sign in
    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        if (AppUtils.validateText(email.getText().toString())) {
            //set validation for email
            if (AppUtils.validateEmail(email.getText().toString())) {
                loginUser.setUsername(email.getText().toString());
                loginUser.setUserType(AppUtils.userType(email.getText().toString()));
            } else {
                email.setError("enter valid email");
            }
        } else {
            email.setError("This field is required");
        }
        //validation for password
        if (AppUtils.validateText(password.getText().toString())) {
            int minPasswordLength = 8;
            if (AppUtils.lengthText(password.getText().toString(), minPasswordLength)) {
                loginUser.setPassword(password.getText().toString());
            } else {
                password.setError("minimum password text length should be " + minPasswordLength);
            }
        } else {
            password.setError("This field is required");
        }

        if (validateUser.validateUser(loginUser)) {
            doLogin();
        } else {
            AppUtils.getSnackBar(findViewById(android.R.id.content), "Invalid Login", BaseTransientBottomBar.LENGTH_LONG);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void doLogin() {
        loadActivityToView();
    }
    //login network call
    private void loadActivityToView() {
        InternetAccess internetAccess = new OnlineMode();

        if(internetAccess.grantInternetAccess(LoginActivity.this)) {
            ServiceLocator.getInstance().getApi(ServiceLocator.GSON).login(email.getText().toString(), password.getText().toString(), accountType).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.d("LOG", "Successfully login : " + response.body().toString());
                            if (response.body().equals(true)) {
                                moveToNextActivity();
                            }
                        } else {
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                }
            });
        } else {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE).setTitleText("Oops...").setContentText("Connection Lost..!").show();
        }
    }

    protected void moveToNextActivity() {
        Intent intent = new Intent(this, (Class<?>) new NavigateUserScreen().selectedUserType(accountType, loginUser));
        startActivity(intent);
        finish();
    }

    //provide spinner selected item
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        accountType = item;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}