package com.example.game_.other01_app.DataObjects;

import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeeklyPlannerObject {


    private DailyPlan mDailyPlan;
    private String mDay;
    public WeeklyPlannerObject(String day, DailyPlan dailyPlan)
    {
        mDailyPlan = dailyPlan; mDay = day;
    }

    public String getmDay() {
        return mDay;
    }

    public DailyPlan getmDailyPlan() {
        return mDailyPlan;
    }
}
