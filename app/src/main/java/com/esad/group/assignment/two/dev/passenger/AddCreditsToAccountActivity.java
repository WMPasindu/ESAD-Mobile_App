package com.esad.group.assignment.two.dev.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.CardDetailsSingleton;
import com.esad.group.assignment.two.dev.modal.Results;
import com.esad.group.assignment.two.dev.network.RetrofitClient;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCreditsToAccountActivity extends AppCompatActivity {

    private CardDetailsSingleton cardDetailsSingleton;
    @BindView(R.id.et_credit_card_number)
    TextInputEditText mCreditCardNumber;
    @BindView(R.id.et_expiery_date)
    TextInputEditText mExpiryDate;
    @BindView(R.id.et_ccv)
    TextInputEditText mEditCCV;
    @BindView(R.id.et_card_holder_name)
    TextInputEditText mCardHolderName;
    @BindView(R.id.et_credit_amount)
    TextInputEditText mCreditAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_creddits_to_account);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btn_pay)
    public void doPay() {
        Toast.makeText(this, "Payed!", Toast.LENGTH_LONG).show();
        validateDate();
        getSuperHeroes();
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successful!")
                .setContentText("Your payment has been successful!")
                .show();
    }

    private void validateDate() {
        if (AppUtils.validateText(mCreditCardNumber.getText().toString())) {
            if (AppUtils.validateCard("visa", mCreditCardNumber.getText().toString())) {
                cardDetailsSingleton.setCardHolderName(mCreditCardNumber.getText().toString());
            } else {
                mCreditCardNumber.setError("Invalid Card Number");
            }
        } else {
            mCreditCardNumber.setError("This field is required");
        }

        if (AppUtils.validateText(mExpiryDate.getText().toString())) {
            cardDetailsSingleton.setCcv(mExpiryDate.getText().toString());
        } else {
            mExpiryDate.setError("This field is required");
        }

        if (AppUtils.validateText(mEditCCV.getText().toString())) {
            if (AppUtils.validateCCV(mEditCCV.getText().toString())) {
                cardDetailsSingleton.setCcv(mEditCCV.getText().toString());
            } else {
                mEditCCV.setError("Invalid Card Number");
            }
        } else {
            mEditCCV.setError("This field is required");
        }

        if (AppUtils.validateText(mCardHolderName.getText().toString())) {
            cardDetailsSingleton.setCcv(mCardHolderName.getText().toString());
        } else {
            mCardHolderName.setError("This field is required");
        }

        if (AppUtils.validateText(mCreditAmount.getText().toString())) {
            cardDetailsSingleton.setCcv(mCreditAmount.getText().toString());
        } else {
            mCreditAmount.setError("This field is required");
        }
    }

    protected void init() {
        cardDetailsSingleton = CardDetailsSingleton.getInstance();
    }

    private void getSuperHeroes() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();
                }

                Log.d("LOG", "Super Heroes : " + oneHeroes[0]);
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                AppUtils.getSnackBar(findViewById(android.R.id.content), "An Error Occurred!", Snackbar.LENGTH_LONG);
            }

        });
    }

}