package com.esad.group.assignment.two.dev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.esad.group.assignment.two.dev.modal.RegisterUser;
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
    @BindView(R.id.et_email)
    TextInputEditText et_email;
    @BindView(R.id.et_password)
    TextInputEditText et_password;
    @BindView(R.id.et_phone_number)
    TextInputEditText et_phone_number;
    @BindView(R.id.et_Display_Name)
    TextInputEditText et_Display_Name;
    @BindView(R.id.sp_type_spinner)
    Spinner sp_type_spinner;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    private String accountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        sp_type_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Type");
        categories.add("Passenger");
        categories.add("Driver");
        categories.add("Inspector");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
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

    @OnClick(R.id.btn_sign_up)
    public void doRegister() {
        validateData();
        create();
    }

    private void validateData() {

    }

    @OnClick(R.id.btn_sign_in)
    public void goSignInScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void create() {
        if(checkBox.isChecked()) {
            RegisterUser registerUser = new RegisterUser.RegisterUserBuilder(
                    et_first_name.getText().toString(),
                    et_email.getText().toString(),
                    et_password.getText().toString(),
                    et_Display_Name.getText().toString(),
                    et_phone_number.getText().toString(),
                    accountType
            ).create();
            Log.d("Log", "display Name : " + registerUser.getUsername());
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please accept the terms and conditions!", Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            snackbar.show();
        }
    }
}