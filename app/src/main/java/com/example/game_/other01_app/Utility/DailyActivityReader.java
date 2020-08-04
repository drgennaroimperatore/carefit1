package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;

import java.util.List;

public class DailyActivityReader extends AsyncTask <Void, Void, List<DailyActivity>> {

    private WeeklyPlanDao mDao;
    private int mDailyPlanID;
    public DailyActivityReader(WeeklyPlanDao dao, int dailyPlanID)
    {
      mDao = dao;
      mDailyPlanID = dailyPlanID;
    }
    @Override
    protected List<DailyActivity> doInBackground(Void... voids) {
        return mDao.getDailyActivitiesByDailyPlanID(mDailyPlanID);
    }
}
