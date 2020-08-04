package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

public class DailyActivityUpdater extends AsyncTask <Void, Void, Void> {
    private WeeklyPlanDao mDao;
    private DailyActivity mDailyActivity;

    public DailyActivityUpdater (WeeklyPlanDao dao, DailyActivity dailyActivity)
    {
        mDao = dao;
        mDailyActivity = dailyActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
         mDao.updateDailyActivity(mDailyActivity);
         return null;
    }
}
