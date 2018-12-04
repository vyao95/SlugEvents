package com.example.cmps121.slugevents;

public class Profile {
    String name;
    String bio;

    public Profile(){}

    public Profile(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }
    public String getBio() {
        return bio;
    }
}

