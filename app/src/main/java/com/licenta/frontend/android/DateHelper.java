package com.licenta.frontend.android;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateHelper {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int getYear(){
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int getMonth(){
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue();
    }

}
