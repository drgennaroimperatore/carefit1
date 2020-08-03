package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.List;

public class WeeklyPlanReader extends AsyncTask<Void,Void, List<WeeklyPlan>>
{
    private WeeklyPlanDao mDao;

    public WeeklyPlanReader(WeeklyPlanDao dao)
    {
     mDao =dao;
    }

    @Override
    protected List<WeeklyPlan> doInBackground(Void... voids) {
        return mDao.getWeeklyPlans();
    }
}
