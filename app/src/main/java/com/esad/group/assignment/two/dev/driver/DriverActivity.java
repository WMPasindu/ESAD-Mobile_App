package com.esad.group.assignment.two.dev.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.utils.MapPopupDialog;
import com.esad.group.assignment.two.dev.utils.QRCodePopupDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class DriverActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.request_leave)
    public void openAbsenceScreen() {
        Intent intent = new Intent(this, DriverAbsenceActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    @OnClick(R.id.examine_token)
    public void driverAccount() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Maintenance").setContentText("Sorry!!! Function still under development").setConfirmText("OKAY").showCancelButton(true).setCancelClickListener(SweetAlertDialog::cancel).show();
    }

    @OnClick(R.id.locate_vehile)
    public void locateVehicle() {
        MapPopupDialog mapPopupDialog = new MapPopupDialog(this);
        mapPopupDialog.show();
    }

    @OnClick(R.id.inform_out_of_service)
    public void informOutOfService() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Maintenance").setContentText("Sorry!!! Function still under development").setConfirmText("OKAY").showCancelButton(true).setCancelClickListener(SweetAlertDialog::cancel).show();
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " "+ appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}