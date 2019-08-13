package com.brownie.mynotesapp.model;

import java.sql.Time;

public class Reminder {

    private String rDescription;

    private String rTime;

    public Reminder() {
    }

    public Reminder(String rDescription, String rTime) {
        this.rDescription = rDescription;
        this.rTime = rTime;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "rDescription='" + rDescription + '\'' +
                ", rTime='" + rTime + '\'' +
                '}';
    }
}
