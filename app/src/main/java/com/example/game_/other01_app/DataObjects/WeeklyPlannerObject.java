package com.example.game_.other01_app.DataObjects;

import com.example.game_.other01_app.Database.entities.DailyActivity;

import java.util.Date;

public class WeeklyPlannerObject {

    public String mDay;
    public Date mDate;
    public WeeklyPlannerObject(String day,Date dt, DailyActivity w)
    {
        mDay =day;
        mDate =dt;
    }
}
