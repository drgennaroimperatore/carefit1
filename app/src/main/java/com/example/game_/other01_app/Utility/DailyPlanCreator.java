package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyPlan;

public class DailyPlanCreator extends AsyncTask <Void, Void, Long> {

    private WeeklyPlanDao mDao;
    private DailyPlan mDailyPlan;

   public DailyPlanCreator ( WeeklyPlanDao dao, DailyPlan dailyPlan)
   {
       mDao = dao;
       mDailyPlan = dailyPlan;
   }
    @Override
    protected Long doInBackground(Void... integers) {
        return mDao.addDailyPlan(mDailyPlan);
    }
}
