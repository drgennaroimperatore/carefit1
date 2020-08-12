package com.example.game_.other01_app.Utility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.game_.other01_app.Activities.PlannerActivity;
import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.DataObjects.WeeklyPlanCreatorResult;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeeklyPlanCreator extends AsyncTask<WeeklyPlan,Void,WeeklyPlanCreatorResult> {

    private WeeklyPlanDao mWeeklyPlanDao;
    private PlannerActivity mActivity;
    private AlertDialog mDialog;




public WeeklyPlanCreator (PlannerActivity activity, WeeklyPlanDao dao)
{
    mWeeklyPlanDao =dao;
}
    @Override
    protected WeeklyPlanCreatorResult doInBackground(WeeklyPlan... weeklyPlans) {
        Long res = mWeeklyPlanDao.addWeeklyPlan(weeklyPlans[0]);

        weeklyPlans[0].id = res.intValue();

        List<DailyPlan> dailyPlans = new ArrayList<>();

        Log.i("CREATORRES", String.valueOf(res));
        Date[] dates = DateTimeAssist.getWeekDates(weeklyPlans[0].startDate);
        for (Date d : dates) {
            DailyPlan dailyPlan = new DailyPlan();
            dailyPlan.dayOfWeek = d;
            dailyPlan.weeklyPlanID = weeklyPlans[0].id;

            dailyPlan.id = (int) mWeeklyPlanDao.addDailyPlan(dailyPlan);
            dailyPlans.add(dailyPlan);
        }
        WeeklyPlanCreatorResult wpcr = new WeeklyPlanCreatorResult(weeklyPlans[0], dailyPlans);
        return wpcr;
    }
}

