package com.esad.group.assignment.two.dev.inspector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.request.Passenger;
import com.esad.group.assignment.two.dev.modal.request.SmartCard;
import com.esad.group.assignment.two.dev.modal.response.SmartCardResponse;
import com.esad.group.assignment.two.dev.modal.response.VerifyTicketResponse;
import com.esad.group.assignment.two.dev.network.ServiceLocator;
import com.esad.group.assignment.two.dev.passenger.CreateTokenActivity;
import com.esad.group.assignment.two.dev.utils.AppConstants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyTicketActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.search_view)
    EditText searchView;
    @BindView(R.id.txt_ticket_number)
    TextView ticketNumber;
    @BindView(R.id.txt_passenger_name)
    TextView passengerName;
    @BindView(R.id.txt_origin_statation)
    TextView origin;
    @BindView(R.id.txt_destination)
    TextView destination;
    @BindView(R.id.txt_booking_date)
    TextView date;
    @BindView(R.id.txt_seat_number)
    TextView seatNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_ticket);
        ButterKnife.bind(this);
        init();
    }

    // innitial data load
    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " "+ appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }

    // cleat textviews
    @OnClick(R.id.btn_verify)
    public void verifyTicket() {
        new SweetAlertDialog(VerifyTicketActivity.this, SweetAlertDialog.WARNING_TYPE).setTitleText("Please Confirm").setContentText("Are you sure do you want to verify this").setConfirmText("Okay").showCancelButton(false).setConfirmClickListener(sDialog -> {
            ticketNumber.setText("N/A");
            passengerName.setText("N/A");
            origin.setText("N/A");
            destination.setText("N/A");
            date.setText("N/A");
            seatNumber.setText("N/A");
            searchView.setText(null);
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good Job!")
                    .setContentText("Successfully Verified")
                    .show();
            sDialog.cancel();
        }).show();
    }

    @OnClick(R.id.signout)
    public void signOut() {
//        popup message
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    //navigate to back
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    //search button
    @OnClick(R.id.search_button)
    public void searchItem() {
        int id = Integer.parseInt(searchView.getText().toString());
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        searchItemById(id);
    }

    // search option -> network call
    private void searchItemById(int id) {
        ServiceLocator.getInstance().getApi(ServiceLocator.GSON).getTicket(14652).enqueue(new Callback<VerifyTicketResponse>() {
            @Override
            public void onResponse(Call<VerifyTicketResponse> call, Response<VerifyTicketResponse> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        ticketNumber.setText(""+response.body().getTicketId());
                        passengerName.setText(response.body().getDestination());
                        origin.setText(response.body().getOrigin());
                        destination.setText(response.body().getDestination());
                        date.setText(response.body().getTravellingDateTime());
                        seatNumber.setText(""+response.body().getSeatNo());
                    } else {
                        Log.d("LOG", "failed : " + response.errorBody());
                        // if error occurred it will display the error message
                        new SweetAlertDialog(VerifyTicketActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VerifyTicketResponse> call, Throwable t) {
                Log.d("LOG", "faled : " + call.request().body().toString());
                // if error occurred it will display the error message
                new SweetAlertDialog(VerifyTicketActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
            }
        });
    }
}