package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

public class DailyActivityDeleter extends AsyncTask <DailyActivity, Void, Void> {

    private WeeklyPlanDao mDao;

    public DailyActivityDeleter (WeeklyPlanDao dao)
    {
        mDao = dao;
    }

    @Override
    protected Void doInBackground(DailyActivity... activities)
    {
        mDao.deleteDailyActivity(activities[0]);
        return null;
    }
}
