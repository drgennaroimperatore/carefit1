package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class UnassignedDailyActivities {
    public int dailyPlanId;
    public Date dayOfWeek;
    public DailyActivityStatus status;

    @Override
    public String toString() {
        return dayOfWeek.toString();
    }
}
