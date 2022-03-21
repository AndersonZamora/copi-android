package com.personal_control.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class return_time {

    private final Context mContext;

    public return_time(Context mContext) {
        this.mContext = mContext;
    }

    public String time() {
        try {
            Calendar mCalendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat mDateFormat = new SimpleDateFormat("hh:mm a");
            return mDateFormat.format(mCalendar.getTime());

        } catch (Exception e) {
            Toast.makeText(mContext, "ERROR", Toast.LENGTH_LONG).show();
            return "00:00:00";
        }
    }
}
