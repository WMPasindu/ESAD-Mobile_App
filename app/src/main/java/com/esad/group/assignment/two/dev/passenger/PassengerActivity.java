package com.esad.group.assignment.two.dev.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class PassengerActivity extends AppCompatActivity {

    // binding view items
    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasenger_profile);
        // enable butternife dependency
        ButterKnife.bind(this);
        // init method to set initial view items to the screen
        init();
    }

    @OnClick(R.id.signout)
    public void signOut() {
        //  SignOut screen navigate option
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    @OnClick(R.id.layout_create_token)
    public void createToken() {
        //  create token screen navigate option
        Intent intent = new Intent(this, CreateTokenActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.layout_bus_schedule)
    public void viewSchedule() {
        //  passenger log token screen navigate option
        Intent intent = new Intent(this, PassengerLogActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.book_ticket)
    public void bookATicket() {
        //  TODO - book a ticket screen navigate option
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Maintenance").setContentText("Sorry!!! Function still under development").setConfirmText("OK").showCancelButton(true).setCancelClickListener(SweetAlertDialog::cancel).show();
    }

    @OnClick(R.id.find_reservation)
    public void findReservation() {
        //  TODO - find reservation screen navigate option
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Maintenance").setContentText("Sorry!!! Function still under development").setConfirmText("OK").showCancelButton(true).setCancelClickListener(SweetAlertDialog::cancel).show();
    }

    protected void init() {
        //  these data will be adding to the view when the initialization
        //  citrate singleton -> userdata and getting all the data and setting to the profile section
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}