package com.esad.group.assignment.two.dev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.esad.group.assignment.two.dev.factory.SignInFactory;
import com.esad.group.assignment.two.dev.interfaces.SignUpUser;
import com.esad.group.assignment.two.dev.modal.request.RegisterUser;
import com.esad.group.assignment.two.dev.modal.request.UserAccount;
import com.esad.group.assignment.two.dev.template.SignUpUserNetwork;
import com.esad.group.assignment.two.dev.template.userTypes.Driver;
import com.esad.group.assignment.two.dev.template.userTypes.Passenger;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.et_first_name)
    TextInputEditText et_first_name;
    @BindView(R.id.et_last_name)
    TextInputEditText et_last_name;
    @BindView(R.id.et_email)
    TextInputEditText et_email;
    @BindView(R.id.et_phone_number)
    TextInputEditText et_phone_number;
    @BindView(R.id.et_nic)
    TextInputEditText et_nic;
    @BindView(R.id.sp_type_spinner)
    Spinner sp_type_spinner;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    private String accountType;
    private RegisterUser registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        // adding data to dropdwon
        sp_type_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select Type");
        categories.add("Passenger");
        categories.add("Driver");
        categories.add("Inspector");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        accountType = item;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    // navigate button sign up
    @OnClick(R.id.btn_sign_up)
    public void doRegister() {
        create();
    }


    // navigate button sign in
    @OnClick(R.id.btn_sign_in)
    public void goSignInScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // create sign in factory this will help to get user type
    private void create() {
        SignInFactory signInFactory = new SignInFactory();
        if (checkBox.isChecked()) {

            SignUpUser userType = signInFactory.getSignInType(accountType);
            String type = userType.selectedSignUpUser();
            if (type.equalsIgnoreCase("Passenger")) {
                createPassengerAccount();
            } else if (type.equalsIgnoreCase("Driver")) {
                createDriverAccount();
            } else if (type.equalsIgnoreCase("Inspector")) {
//                  TODO : inspector
            } else if (type.equalsIgnoreCase("Transport Manager")) {
//                  TODO : Transport Manager -> not implemented
            }else {
//                TODO :
            }
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please accept the terms and conditions!", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            snackbar.show();
        }
    }
    // create passenger account and singleton
    private void createPassengerAccount() {
        registerUser = new RegisterUser.RegisterUserBuilder(
                new UserAccount(et_email.getText().toString()),
                et_first_name.getText().toString(),
                et_last_name.getText().toString(),
                et_phone_number.getText().toString(),
                et_nic.getText().toString()
        ).create();
        SignUpUserNetwork signUpUserNetwork = new Passenger();
        signUpUserNetwork.network(SignUpActivity.this, LoginActivity.class, registerUser);
    }

    // create driver account and singleton
    private void createDriverAccount() {
        registerUser = new RegisterUser.RegisterUserBuilder(
                new UserAccount(et_email.getText().toString()),
                et_first_name.getText().toString(),
                et_last_name.getText().toString(),
                et_phone_number.getText().toString(),
                et_nic.getText().toString()
        ).create();
        SignUpUserNetwork signUpUserNetwork = new Driver();
        signUpUserNetwork.network(SignUpActivity.this, LoginActivity.class, registerUser);
    }
}