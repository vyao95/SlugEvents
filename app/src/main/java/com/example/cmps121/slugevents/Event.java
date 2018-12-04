package com.example.cmps121.slugevents;

public class Event {
    String id;
    String name;
    String time;
    String location;
    String date;
    String email;
    String tag;
    String timeEnd;
    public Event() {

    }
    public Event(String id, String name, String time, String timeEnd,String location, String date, String email, String tag) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.timeEnd = timeEnd;
        this.location = location;
        this.date = date;
        this.email = email;
        this.tag = tag;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getLocation() { return location; }

    public String getDate() {
        return date;
    }

    public String getEmail() {return email;}

    public String getTag() {return tag;}
}

