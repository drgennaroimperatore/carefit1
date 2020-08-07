package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

public class DailyActivityCreator extends AsyncTask<DailyActivity, Void, Long> {
    private WeeklyPlanDao mDao;


    public DailyActivityCreator (WeeklyPlanDao dao)
    {
        mDao = dao;
    }

    @Override
    protected Long doInBackground(DailyActivity... activities) {
        return mDao.addDailyActivity(activities[0]);
    }
}
