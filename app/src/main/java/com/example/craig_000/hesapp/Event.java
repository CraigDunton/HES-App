package com.example.craig_000.hesapp;

/**
 * Created by craig_000 on 10/22/2016.
 */

public class Event {

    private String date;
    private String time;
    private String title;
    private String description;
    private String location;
    private String id;
    public Event(){}

    public Event(String date, String time, String title, String description, String location, String id){
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
        this.location = location;
        this.id = id;
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
    public String getDescription(){
        return this.description;
    }
    public String getLocation(){
        return this.location;
    }
    public String getID(){
        return this.id;
    }

}
