package com.esad.group.assignment.two.dev.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AppUtils {

    // validate email
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

    // validate text
    public static boolean validateText(String text) {
        return text.length() > 0;
    }

    // validate email pattern
    public static boolean validateEmail(String text) {
        return text.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    }

    // validate visa card
    public static boolean validateCard(String type, String text) {
        if (type.equalsIgnoreCase("visa")) {
            return text.matches("^4[0-9]{6,}$");
        }
        if (type.equalsIgnoreCase("mastercard")) {
            return text.matches("^3[47][0-9]{5,}$");
        } else {
            return false;
        }
    }

    // text length
    public static boolean lengthText(String text, int requiredLength) {
        return text.length() >= requiredLength;
    }

    // validte ccv
    public static boolean validateCCV(String text) {
        return text.matches("^[0-9]{3,4}$");
    }

    // create snack bar
    public static <T extends View> void getSnackBar(T viewById, String message, int length) {
        Snackbar snackbar = Snackbar.make(viewById, message, length);
        snackbar.show();
    }

    // create datetime picker and set data to text view
    public static void commonDateTimePicker(Context context, DatePickerDialog picker, TextView textView) {
        final Calendar cldr = Calendar.getInstance();
        String  day = ""+cldr.get(Calendar.DAY_OF_MONTH);
        String  month = ""+cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        if(cldr.get(Calendar.MONTH) < 10){
            month = "0" + month;
        }
        if(cldr.get(Calendar.DAY_OF_MONTH) < 10){
            day  = "0" + day ;
        }

        String finalDay = day;
        picker = new DatePickerDialog(context, (DatePickerDialog.OnDateSetListener)
                (datePicker, years, monthOfYear, dayOfMonth) ->
                        textView.setText(year + "-" + (monthOfYear + 1) + "-" + finalDay)
                , year, Integer.parseInt(month), Integer.parseInt(day));
        picker.show();
    }

    // create time picker
    public static void commonTimePicker(Context context, TimePickerDialog picker, TextView textView) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        picker = new TimePickerDialog(context,
                (view, hourOfDay, minute) -> textView.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
        picker.show();
    }

}
