package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyPlan;


import java.util.List;

public class DailyPlanReader extends AsyncTask <Void, Void, List<DailyPlan>>
{
    private WeeklyPlanDao mDao;
    private int mWeeklyPlanID;

    public DailyPlanReader(WeeklyPlanDao dao, int weeklyPlanID)
    {
      mDao = dao;
      mWeeklyPlanID = weeklyPlanID;
    }
    @Override
    protected List<DailyPlan> doInBackground(Void... voids) {
        return mDao.getDailyPlansByWeeklyPlanID(mWeeklyPlanID);
    }
}
