package com.esad.group.assignment.two.dev.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.interfaces.TokenType;
import com.esad.group.assignment.two.dev.factory.TokenFactory;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.esad.group.assignment.two.dev.utils.QRCodePopupDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;

    private TokenFactory tokenFactory;
    private String selectedTokenType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_token);
        ButterKnife.bind(this);
        init();
        sp_token_spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Type");
        categories.add("Smart Card");
        categories.add("Bar Code");
        categories.add("QR Code");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_token_spinner.setAdapter(dataAdapter);
        tokenFactory = new TokenFactory();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (!item.equalsIgnoreCase("Select Type")) {
            TokenType tokenType = tokenFactory.getTokenType(item);
            selectedTokenType = tokenType.selectedTokenType();
        }
    }

    @OnClick(R.id.btn_reserve)
    public void reserveTicket() {
        selectTicketType();
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
        if (selectedTokenType.equalsIgnoreCase("Bar_Code")) {
            doBarcode();
        } else if (selectedTokenType.equalsIgnoreCase("Smart_Card")) {
            doSmartCard();
        } else if (selectedTokenType.equalsIgnoreCase("QR_Code")) {
            doQRCode();
        }
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " "+ appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }

    private void doQRCode() {
        QRCodePopupDialog qrCodePopupDialog = new QRCodePopupDialog(this);
        qrCodePopupDialog.show();
    }

    private void doSmartCard() {
        Intent intent = new Intent(this, AddCreditsToAccountActivity.class);
        startActivity(intent);
    }

    private void doBarcode() {
        QRCodePopupDialog qrCodePopupDialog = new QRCodePopupDialog(this);
        qrCodePopupDialog.show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}