package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;

import java.util.List;

public class UnassignedDailyActivitiesReader extends AsyncTask <Void, Void, List<UnassignedDailyActivities>> {

    private WeeklyPlanDao mDao;
    public UnassignedDailyActivitiesReader (WeeklyPlanDao dao)
    {
        mDao = dao;
    }
    
    @Override
    protected List<UnassignedDailyActivities> doInBackground(Void... voids) {
        return mDao.getAllUnassignedActivities();
    }
}
