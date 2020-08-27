package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

public class DailyActivityRescheduler extends AsyncTask<Void, Void, Void> {
    WeeklyPlanDao mWeeklyPlanDao;
    DailyActivity mActivityToUpdate;
    int mNewID =0;
    public DailyActivityRescheduler(WeeklyPlanDao dao, DailyActivity activity, int newID)
    {
        mWeeklyPlanDao = dao;
        mActivityToUpdate = activity;
        mNewID = newID;
    }
    @Override
    protected Void doInBackground(Void... voids)
    {
        mActivityToUpdate.dailyPlanId = mNewID;
        mWeeklyPlanDao.updateDailyActivity(mActivityToUpdate);
        return null;
    }
}
