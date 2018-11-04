package com.example.cmps121.slugevents;

public class Event {
    String id;
    String name;
    String time;
    String location;
    String date;
    String email;

    public Event() {

    }
    public Event(String id, String name, String time, String location, String date, String email) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
        this.date = date;
        this.email = email;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() { return location; }

    public String getDate() {
        return date;
    }

    public String getEmail() {return email;}
}

