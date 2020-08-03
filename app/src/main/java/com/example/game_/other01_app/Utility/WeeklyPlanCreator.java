package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;
import android.util.Log;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.Date;
import java.util.List;

public class WeeklyPlanCreator extends AsyncTask<WeeklyPlan,Void,Long> {

    private WeeklyPlanDao mWeeklyPlanDao;

public WeeklyPlanCreator (WeeklyPlanDao dao)
{
    mWeeklyPlanDao =dao;
}
    @Override
    protected Long doInBackground(WeeklyPlan... weeklyPlans) {
      Long res = mWeeklyPlanDao.addWeeklyPlan(weeklyPlans[0]);
      Log.i("CREATORRES", String.valueOf(res));
      return res;
    }
}

