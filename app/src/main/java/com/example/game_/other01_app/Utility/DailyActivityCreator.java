package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

public class DailyActivityCreator extends AsyncTask<Void, Void, Long> {
    private WeeklyPlanDao mDao;
    private DailyActivity mDailyActivity;

    public DailyActivityCreator (WeeklyPlanDao dao, DailyActivity dailyActivity)
    {
        mDao = dao; mDailyActivity = dailyActivity;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        return mDao.addDailyActivity(mDailyActivity);
    }
}
