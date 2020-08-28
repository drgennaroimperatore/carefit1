package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;

import java.util.Date;

@Entity
public class UnassignedDailyActivities {
    public int id;
    public int dailyPlanId;
    public Date dayOfWeek;
    public DailyActivityStatus status;

    @Override
    public String toString() {
        return DateTimeAssist.convertDateToPreferredFormat(dayOfWeek) +" "+ (id);
    }
}
