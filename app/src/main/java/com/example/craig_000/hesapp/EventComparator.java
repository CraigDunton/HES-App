package com.example.craig_000.hesapp;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by craig_000 on 3/28/2017.
 */
public class EventComparator implements java.util.Comparator<Event> {
    @Override
    public int compare(Event lhs, Event rhs) {

        int result=0;

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        String leftDate = lhs.getDate();
        String rightDate = rhs.getDate();
        try {
            Date lDate = format.parse(leftDate);
            Date rDate = format.parse(rightDate);
            result=lDate.compareTo(rDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("EventAdded","Trouble parsing...");
        }

        return result;
    }
}
