package com.example.game_.other01_app.Utility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.game_.other01_app.Activities.PlannerActivity;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.Date;
import java.util.List;

public class WeeklyPlanCreator extends AsyncTask<WeeklyPlan,Void,Long> {

    private WeeklyPlanDao mWeeklyPlanDao;
    private PlannerActivity mActivity;
    private AlertDialog mDialog;




public WeeklyPlanCreator (PlannerActivity activity, WeeklyPlanDao dao)
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

