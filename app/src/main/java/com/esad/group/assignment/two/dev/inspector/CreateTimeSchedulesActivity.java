package com.esad.group.assignment.two.dev.inspector;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTimeSchedulesActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.txt_date)
    TextView mDate;
    @BindView(R.id.txt_time)
    TextView mTime;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_schedules);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btn_date)
    public void addDate() {
        AppUtils.commonDateTimePicker(this, datePicker, mDate);
    }

    @OnClick(R.id.btn_time)
    public void addTime() {
        AppUtils.commonTimePicker(this, timePicker, mTime);
    }

    @OnClick(R.id.btn_create_schedule)
    public void createSchedule() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successful!")
                .setContentText("New Schedule Added Successfully")
                .show();
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}