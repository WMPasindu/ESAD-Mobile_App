package com.esad.group.assignment.two.dev.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.adapter.PreviousLeaveAdapter;
import com.esad.group.assignment.two.dev.adapter.TokenHistoryAdapter;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.LeaveHistory;
import com.esad.group.assignment.two.dev.modal.PreviousTokens;
import com.esad.group.assignment.two.dev.modal.request.Absence;
import com.esad.group.assignment.two.dev.modal.request.Driver;
import com.esad.group.assignment.two.dev.modal.request.RegisterUser;
import com.esad.group.assignment.two.dev.modal.request.UserAccount;
import com.esad.group.assignment.two.dev.modal.response.AbsenceResponse;
import com.esad.group.assignment.two.dev.modal.response.AccountResponse;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.utils.AppConstants;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.et_remark)
    TextView mRemark;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    private DatePickerDialog datePicker;
    private String selectedItem;
    private Absence absence;
    private List<Object> viewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_absence);
        ButterKnife.bind(this);
        init();
    }

    //navigate back
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    //signout
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    //add to date
    @OnClick(R.id.btn_to_date)
    public void selectToDate() {
        toDate();
    }

    // add from date
    @OnClick(R.id.btn_from_date)
    public void selectFromDate() {
        fromDate();
    }

    // apply leaves option
    @OnClick(R.id.btn_apply_leave)
    public void applyLeaves() {
        applyLeave();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    // innitial screens
    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
        leaveType();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.token_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new PreviousLeaveAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();
    }

    // adding spiiner data
    private void leaveType() {
        sp_type_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select Leave Type");
        categories.add("Casual");
        categories.add("Sick");
        categories.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type_spinner.setAdapter(dataAdapter);
    }

    // set date from date picker
    protected void fromDate() {
        AppUtils.commonDateTimePicker(this, datePicker, fromDate);
    }

    protected void toDate() {
        AppUtils.commonDateTimePicker(this, datePicker, toDate);
    }

    // apply leave option for driver
    protected void applyLeave() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int driverID = preferences.getInt(AppConstants.DRIVER_SP, 2);
        absence = new Absence();
        Driver driver = new Driver();
        driver.setDriverId(2);
        absence.setDriver(driver);
        absence.setLeaveType(selectedItem);
        absence.setFromDate(fromDate.getText().toString());
        absence.setToDate(toDate.getText().toString());
        absence.setRemark(mRemark.getText().toString());
        doDriverAbsence();
    }

    protected void doDriverAbsence() {
        ServiceLocator.getInstance().getApi(ServiceLocator.GSON).addAbsence(absence).enqueue(new Callback<AbsenceResponse>() {
            @Override
            public void onResponse(Call<AbsenceResponse> call, Response<AbsenceResponse> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("LOG", "Successfully : " + response.body().toString());
                        new SweetAlertDialog(DriverAbsenceActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Successful!")
                                .setContentText("Your " + response.body().getLeaveType() + " leave(s) from has been added to the system.")
                                .show();
                    } else {
                        Log.d("LOG", "unsuccessfully : " + response.errorBody().toString());
                        new SweetAlertDialog(DriverAbsenceActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new SweetAlertDialog(DriverAbsenceActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                }
            }
            @Override
            public void onFailure(Call<AbsenceResponse> call, Throwable t) {
                Log.d("LOG", "unsuccessfully : ");
                new SweetAlertDialog(DriverAbsenceActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
            }
        });
    }

    private void addItemsFromJSON() {
        // getting data from JSON and setting to the model
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            // looping through the json file to create models
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String type = itemObj.getString("type");
                String fromDate = itemObj.getString("fromDate");
                String toDate = itemObj.getString("toDate");
                LeaveHistory leaveHistory = new LeaveHistory(type, fromDate, toDate);
                viewItems.add(leaveHistory);
            }
        } catch (JSONException | IOException e) {
            // display errors
            Log.d("LOG", "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        // read JSON file to fetch data
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.driver_leave_histiry_data);
            //reading data --
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((jsonString = bufferedReader.readLine()) != null) {
                    builder.append(jsonString);
                }
            } catch (Exception e) {
                // display errors
                Log.d("LOG", "Error : ", e);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}