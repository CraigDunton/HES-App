package com.example.craig_000.hesapp;

/**
 * Created by craig_000 on 10/22/2016.
 */

public class Event {

    private String date;
    private String time;
    private String title;

    public Event(String date, String time, String title){
        this.date = date;
        this.time = time;
        this.title = title;
    }

    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public String getTitle(){
        return this.title;
    }

}
