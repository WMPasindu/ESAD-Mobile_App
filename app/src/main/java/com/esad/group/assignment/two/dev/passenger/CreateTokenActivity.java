package com.esad.group.assignment.two.dev.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.adapter.PassengerLogAdapter;
import com.esad.group.assignment.two.dev.adapter.TokenHistoryAdapter;
import com.esad.group.assignment.two.dev.interfaces.TokenType;
import com.esad.group.assignment.two.dev.factory.Token;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.PassengerLog;
import com.esad.group.assignment.two.dev.modal.PreviousTokens;
import com.esad.group.assignment.two.dev.modal.request.Passenger;
import com.esad.group.assignment.two.dev.modal.request.SmartCard;
import com.esad.group.assignment.two.dev.modal.response.SmartCardResponse;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.utils.AppConstants;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.esad.group.assignment.two.dev.utils.QRCodePopupDialog;
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
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTokenActivity extends AppCompatActivity implements OnItemSelectedListener {

    @BindView(R.id.sp_token_spinner)
    Spinner sp_token_spinner;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.email)
    TextView mEmail;
    @BindView(R.id.txt_date)
    TextView mDate;
    @BindView(R.id.txt_time)
    TextView mTime;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.token_id)
    TextView mTokenId;
    @BindView(R.id.et_amount)
    TextView mAmount;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private List<Object> viewItems = new ArrayList<>();

    private Token tokenFactory;
    private String selectedTokenType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_token);
        ButterKnife.bind(this);
        init();
        // adding spinner data
        sp_token_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select Type");
        categories.add("Smart Card");
        categories.add("Bar Code");
        categories.add("QR Code");

        // adding data to adapter
        //this helps to display list of available token types
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_token_spinner.setAdapter(dataAdapter);
        tokenFactory = new Token();
        createRandomNumber();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (!item.equalsIgnoreCase("Select Type")) {
            TokenType tokenType = tokenFactory.getTokenType(item);
            selectedTokenType = tokenType.selectedTokenType();
        }
    }

    // reservation option
    @OnClick(R.id.btn_reserve)
    public void makeReservation() {
        selectTicketType();
    }

    // nativate to previous activity
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    // signout option
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    @OnClick(R.id.btn_date)
    public void selectDate() {
        AppUtils.commonDateTimePicker(this, datePicker, mDate);
    }

    @OnClick(R.id.btn_time)
    public void selectTime() {
        AppUtils.commonTimePicker(this, timePicker, mTime);
    }

    protected void selectTicketType() {
        // according to the token type navigation option will work
        // TODO - this section still only available for smart cards -> need futher improvements for BAR and QRR codes
        if (selectedTokenType.equalsIgnoreCase("Bar_Code")) {
            scanToken();
        } else if (selectedTokenType.equalsIgnoreCase("Smart_Card")) {
            doSmartCard();
        } else if (selectedTokenType.equalsIgnoreCase("QR_Code")) {
            scanQR();
        } else {
            //display error
        }
    }

    private void createRandomNumber() {
        // generating random token id ->
        //TODO- there is an oppotunity to create two tokens with same id, have to address that issue
        final int min = 10000;
        final int max = 99999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        mTokenId.setText("tb-" + random);
    }

    private void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.token_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new TokenHistoryAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();
    }

    private void scanQR() {
        // TODO - only navigating option available -> Bar code scanning option - not still completed the development
        QRCodePopupDialog qrCodePopupDialog = new QRCodePopupDialog(this);
        qrCodePopupDialog.show();
    }

    private void doSmartCard() {
        // generate smart token -> TODO : validate and create more user friendly view
        addData();
    }

    private void scanToken() {
        // TODO - only navigating option available -> QR code scanning option - not still completed the development
        QRCodePopupDialog qrCodePopupDialog = new QRCodePopupDialog(this);
        qrCodePopupDialog.show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    // network call
    private void addData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int passengerId = preferences.getInt(AppConstants.PASSENGER_SP, 1);
        int creditAmount = Integer.parseInt(mAmount.getText().toString());
        SmartCard smartCard = new SmartCard();
        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerId);
        smartCard.setValidityPeriod(mDate.getText().toString());
        smartCard.setCredits(creditAmount);
        smartCard.setPassenger(passenger);
        smartCard.setActivated(mDate.getText().toString());
        ServiceLocator.getInstance().getApi(ServiceLocator.GSON).smartCard(smartCard).enqueue(new Callback<SmartCardResponse>() {
            @Override
            public void onResponse(Call<SmartCardResponse> call, Response<SmartCardResponse> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        new SweetAlertDialog(CreateTokenActivity.this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("WelDone!").setContentText("You have successfully created the token. Please make a payment.").setConfirmText("Okay").showCancelButton(false).setConfirmClickListener(sDialog -> {
                            // if success moving to next screen fro payment
                            moveToNextScreen();
                            sDialog.cancel();
                        }).show();
                    } else {
                        Log.d("LOG", "failed : " + response.errorBody());
                        // if error occured it will display the error message
                        new SweetAlertDialog(CreateTokenActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SmartCardResponse> call, Throwable t) {
                Log.d("LOG", "faled : " + call.request().body().toString());
                // if error occured it will display the error message
                new SweetAlertDialog(CreateTokenActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
            }
        });
    }

    private void moveToNextScreen() {
        Intent intent = new Intent(this, AddCreditsToAccountActivity.class);
        intent.putExtra(AppConstants.PAYMENT, mAmount.getText().toString());
        startActivity(intent);
    }

    private void addItemsFromJSON() {
        // getting data from JSON and setting to the model
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            // looping through the json file to create models
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String id = itemObj.getString("id");
                String date = itemObj.getString("date");
                String departure = itemObj.getString("credit");
                PreviousTokens previousTokens = new PreviousTokens(id, date, departure);
                viewItems.add(previousTokens);
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
            inputStream = getResources().openRawResource(R.raw.previous_token_data);
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