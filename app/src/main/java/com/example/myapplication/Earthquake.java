package com.example.myapplication;

public class Earthquake {
    private double magnittude;
    private String city;
    private long date;

    public Earthquake(double magnittude, String city, long date) {
        this.magnittude = magnittude;
        this.city = city;
        this.date = date;
    }

    public double getMagnittude() {
        return magnittude;
    }

    public String getCity() {
        return city;
    }

    public long getDate() {
        return date;
    }
}
