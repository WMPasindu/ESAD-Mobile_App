package com.esad.group.assignment.two.dev.transportManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.interfaces.InternetAccess;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.request.TimeTable;
import com.esad.group.assignment.two.dev.modal.response.TimeTableResponse;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.proxy.OnlineMode;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTimeSchedulesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.txt_date)
    TextView mDate;
    @BindView(R.id.txt_arrival_time)
    TextView mArrivalTime;
    @BindView(R.id.txt_departure_time)
    TextView mDepartureTime;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.sp_route_spinner)
    Spinner mRouteSpinner;

    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private String routeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_schedules);
        ButterKnife.bind(this);
        init();
    }

    // navigate previous activity
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    // signout from the application
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }
    // navigate button date
    @OnClick(R.id.btn_date)
    public void addDate() {
        AppUtils.commonDateTimePicker(this, datePicker, mDate);
    }

    // navigate button arrival
    @OnClick(R.id.btn_arrival_time)
    public void addArrivalTime() {
        AppUtils.commonTimePicker(this, timePicker, mArrivalTime);
    }

    // navigate button departure
    @OnClick(R.id.btn_departure_time)

    // select a date
    public void addDepartureTime() {
        AppUtils.commonTimePicker(this, timePicker, mDepartureTime);
    }

    // navigate button create schedules
    @OnClick(R.id.btn_create_schedule)
    public void createSchedule() {
        loadActivityToView();
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
        //adding data to dropdown
        mRouteSpinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select A Route");
        categories.add("177");
        categories.add("190");
        categories.add("170");
        categories.add("336");
        categories.add("993");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRouteSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        routeId = item;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    // network call and create object
    private void loadActivityToView() {
        InternetAccess internetAccess = new OnlineMode();
        TimeTable timeTable = new TimeTable();
        timeTable.setRouteId(routeId);
        timeTable.setDayOfWeek(mDate.getText().toString());
        timeTable.setArrivalTime(mArrivalTime.getText().toString());
        timeTable.setDepartureTime(mDepartureTime.getText().toString());

        if (internetAccess.grantInternetAccess(CreateTimeSchedulesActivity.this)) {
            ServiceLocator.getInstance().getApi(ServiceLocator.GSON).createTimeTable(timeTable).enqueue(new Callback<TimeTableResponse>() {
                @Override
                public void onResponse(Call<TimeTableResponse> call, Response<TimeTableResponse> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.d("LOG", "Successfully login : " + response.body().toString());
                            new SweetAlertDialog(CreateTimeSchedulesActivity.this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Successful").setContentText("Time Schedule placed successfully").show();
                        } else {
                            new SweetAlertDialog(CreateTimeSchedulesActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SweetAlertDialog(CreateTimeSchedulesActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                }

                @Override
                public void onFailure(Call<TimeTableResponse> call, Throwable t) {
                    new SweetAlertDialog(CreateTimeSchedulesActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                }
            });
        } else {
            new SweetAlertDialog(CreateTimeSchedulesActivity.this, SweetAlertDialog.WARNING_TYPE).setTitleText("Oops...").setContentText("Connection Lost..!").show();
        }
    }
}