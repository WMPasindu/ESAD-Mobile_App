package com.esad.group.assignment.two.dev.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.CardDetailsSingleton;
import com.esad.group.assignment.two.dev.utils.AppConstants;
import com.esad.group.assignment.two.dev.utils.AppUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

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

    // goback
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    // signout
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    // make payment option
    @OnClick(R.id.btn_pay)
    public void doPay() {
        Toast.makeText(this, "Payed!", Toast.LENGTH_LONG).show();
        validateDate();
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successful!")
                .setContentText("Your payment has been successful!")
                .show();
    }

    // validate date option
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

    // innitial screens
    protected void init() {
        cardDetailsSingleton = CardDetailsSingleton.getInstance();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String value = bundle.getString(AppConstants.PAYMENT);
            mCreditAmount.setText("Rs. " +currencyFormat(value));
        }
    }

    // make currensy format and adding cents values to it
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

}