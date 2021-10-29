package com.esad.group.assignment.two.dev.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AppUtils {

    public static String userType(String email) {
        if (email.equalsIgnoreCase("passenger@gmail.com")) {
            return "Passenger";
        } else if (email.equalsIgnoreCase("driver@gmail.com")) {
            return "driver";
        } else if (email.equalsIgnoreCase("inspector@gmail.com")) {
            return "inspector";
        } else {
            return null;
        }
    }

    public static boolean validateText(String text) {
        if (text.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateEmail(String text) {
        if (text.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateCard(String type, String text) {
        if (type.equalsIgnoreCase("visa")) {
            if (text.matches("^4[0-9]{6,}$")) {
                return true;
            } else {
                return false;
            }
        }
        if (type.equalsIgnoreCase("mastercard")) {
            if (text.matches("^3[47][0-9]{5,}$")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean lengthText(String text, int requiredLength) {
        if (text.length() >= requiredLength) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateCCV(String text) {
        if (text.matches("^[0-9]{3,4}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static <T extends View> void getSnackBar(T viewById, String message, int length) {
        Snackbar snackbar = Snackbar.make(viewById, message, length);
        snackbar.show();
    }

    public static void commonDateTimePicker(Context context, DatePickerDialog picker, TextView textView) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(context, (DatePickerDialog.OnDateSetListener)
                (datePicker, years, monthOfYear, dayOfMonth) ->
                        textView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                , year, month, day);
        picker.show();
    }

    public static void commonTimePicker(Context context, TimePickerDialog picker, TextView textView) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        picker = new TimePickerDialog(context,
                (view, hourOfDay, minute) -> textView.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
        picker.show();
    }

}
