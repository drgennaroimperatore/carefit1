package com.example.game_.other01_app.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.game_.other01_app.Activities.DashboardActivity;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyPlan;

public class DailyPlanCreator extends AsyncTask <Void, Void, Long> {

    private WeeklyPlanDao mDao;
    private DailyPlan mDailyPlan;
    private AlertDialog mDialog;
    private Context mContext;

   public DailyPlanCreator (Context context, WeeklyPlanDao dao, DailyPlan dailyPlan)
   {
       mDao = dao;
       mDailyPlan = dailyPlan;
       mContext = context;
   }
    @Override
    protected Long doInBackground(Void... integers) {
        return mDao.addDailyPlan(mDailyPlan);
    }



}
