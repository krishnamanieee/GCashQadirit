package com.rohasoft.www.gcash.modal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by krish on 2/8/2018.
 */

public class Commons {

    public static String getDate(String date) {
        String strDate = "";
        Date date1 = null;
        if (date.isEmpty()) {
            return "";
        }
        try {
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            date1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            date1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            date1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            date1 = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            strDate = outputFormat.format(date1);
        } catch (Exception e) {
            return "";
        }
        return strDate;
    }
}
