package com.esad.group.assignment.two.dev.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class DriverAbsenceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.sp_type_spinner)
    Spinner sp_type_spinner;
    @BindView(R.id.txt_to_date)
    TextView toDate;
    @BindView(R.id.txt_from_date)
    TextView fromDate;
    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_absence);
        ButterKnife.bind(this);
        init();
        sp_type_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Leave Type");
        categories.add("Casual");
        categories.add("Sick");
        categories.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_spinner.setAdapter(dataAdapter);
    }

    @OnClick(R.id.btn_to_date)
    public void selectToDate() {
        AppUtils.commonDateTimePicker(this, datePicker, toDate);
    }

    @OnClick(R.id.btn_from_date)
    public void selectFromDate() {
        AppUtils.commonDateTimePicker(this, datePicker, fromDate);
    }

    @OnClick(R.id.btn_apply_leave)
    public void applyLeaves() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successful!")
                .setContentText("Your leaves has been added to the system.")
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " "+ appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}