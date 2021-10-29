package com.esad.group.assignment.two.dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.esad.group.assignment.two.dev.driver.DriverActivity;
import com.esad.group.assignment.two.dev.inspector.InspectorActivity;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.LoginUser;
import com.esad.group.assignment.two.dev.passenger.PassengerProfileActivity;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.esad.group.assignment.two.dev.utils.ValidateUser;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private LoginUser loginUser;
    private ValidateUser validateUser;
    @BindView(R.id.et_email)
    TextInputEditText email;
    @BindView(R.id.et_password)
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        loginUser = new LoginUser();
        validateUser = new ValidateUser();
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        if (AppUtils.validateText(email.getText().toString())) {
            if (AppUtils.validateEmail(email.getText().toString())) {
                loginUser.setUsername(email.getText().toString());
                loginUser.setUserType(AppUtils.userType(email.getText().toString()));
            } else {
                email.setError("enter valid email");
            }
        } else {
            email.setError("This field is required");
        }
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
            doLogin(loginUser);
        } else {
            AppUtils.getSnackBar(findViewById(android.R.id.content), "Invalid Login",  Snackbar.LENGTH_LONG);
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void doLogin(LoginUser user) {
        if (user.getUserType().equalsIgnoreCase("Passenger")) {
            createUser("Michel", "Starc", "071-5831012", "931245678V", loginUser.getUsername(),
                    "https://196034-584727-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2019/05/Business-Development-Manager-Profile-Photo.jpg");
            loadActivityToView(PassengerProfileActivity.class);
        } else if (user.getUserType().equalsIgnoreCase("Driver")) {
            createUser("Mike", "Arthur", "071-1234012", "680227872V", loginUser.getUsername(),
                    "https://buffer.com/library/content/images/2020/05/Ash-Read.png");
            loadActivityToView(DriverActivity.class);
        } else if (user.getUserType().equalsIgnoreCase("Inspector")) {
            createUser("Mahela", "Jayawardena", "071-6790354", "841899608V", loginUser.getUsername(),
                    "https://196034-584727-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2018/01/IT-QA-Analist-profile-picture-round.jpg");
            loadActivityToView(InspectorActivity.class);
        }
    }

    private void loadActivityToView(Object CreateTokenActivity) {
        Intent intent = new Intent(this, (Class<?>) CreateTokenActivity);
        startActivity(intent);
        finish();
    }

    protected void createUser(String fname, String lname, String phone, String nic, String username, String profImage) {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        appUserSingleton.setUserId(1L);
        appUserSingleton.setFirstName(fname);
        appUserSingleton.setLastname(lname);
        appUserSingleton.setMobileNumber(phone);
        appUserSingleton.setNic(nic);
        appUserSingleton.setEmail(username);
        appUserSingleton.setProfileImage(profImage);
    }

}