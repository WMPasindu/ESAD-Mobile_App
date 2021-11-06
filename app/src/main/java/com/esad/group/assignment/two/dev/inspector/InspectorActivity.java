package com.esad.group.assignment.two.dev.inspector;

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

public class InspectorActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.search_token)
    public void verifyTokens() {
        Intent intent = new Intent(this, VerifyTicketActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    @OnClick(R.id.lv_tickets_history)
    public void searchTicketDetails() {
        Intent intent = new Intent(this, ViewPreviousApprovedTicketsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_create_schedule)
    public void createSchedules() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Maintenance").setContentText("Sorry!!! Function still under development").setConfirmText("OK").showCancelButton(true).setCancelClickListener(SweetAlertDialog::cancel).show();
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}