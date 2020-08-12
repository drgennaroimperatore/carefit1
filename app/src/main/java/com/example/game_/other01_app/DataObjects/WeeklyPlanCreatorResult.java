package com.example.game_.other01_app.DataObjects;

import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.List;

public class WeeklyPlanCreatorResult {
    private WeeklyPlan mWeeklyPlan;
    private List<DailyPlan> mDailyPlans;

    public WeeklyPlanCreatorResult(WeeklyPlan wp, List<DailyPlan> dp)
    {
        mWeeklyPlan = wp;
        mDailyPlans =dp;
    }

    public void setmDailyPlans(List<DailyPlan> mDailyPlans) {
        this.mDailyPlans = mDailyPlans;
    }

    public void setmWeeklyPlan(WeeklyPlan mWeeklyPlan) {
        this.mWeeklyPlan = mWeeklyPlan;
    }

    public List<DailyPlan> getmDailyPlans() {
        return mDailyPlans;
    }

    public WeeklyPlan getmWeeklyPlan() {
        return mWeeklyPlan;
    }
}
