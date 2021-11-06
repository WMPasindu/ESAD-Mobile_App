package com.esad.group.assignment.two.dev.transportManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.inspector.VerifyTicketActivity;
import com.esad.group.assignment.two.dev.inspector.ViewPreviousApprovedTicketsActivity;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class TransportManagerActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_manager);
        ButterKnife.bind(this);
        init();
    }

    // navigate search token activity
    @OnClick(R.id.search_token)
    public void verifyTokens() {
        Intent intent = new Intent(this, VerifyTicketActivity.class);
        startActivity(intent);
    }

    // signout from the application
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    // check ticket history
    @OnClick(R.id.lv_tickets_history)
    public void searchTicketDetails() {
//        Intent intent = new Intent(this, ViewPreviousApprovedTicketsActivity.class);
//        startActivity(intent);
        // TODO : method not allowd to trasport manager
    }

    // navigate to create schedule activity
    @OnClick(R.id.btn_create_schedule)
    public void createSchedules() {
        Intent intent = new Intent(this, CreateTimeSchedulesActivity.class);
        startActivity(intent);
    }

    // load initial views
    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}