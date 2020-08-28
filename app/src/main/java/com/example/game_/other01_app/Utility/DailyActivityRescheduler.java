package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

public class DailyActivityRescheduler extends AsyncTask<Void, Void, Void> {
    WeeklyPlanDao mWeeklyPlanDao;
    DailyActivity mActivityToReassign;
    UnassignedDailyActivities mSelectedUnassignedActivity;
    int selectedPlanID = 0;


    public DailyActivityRescheduler(WeeklyPlanDao dao, DailyActivity activity, UnassignedDailyActivities unassignedDailyActivities, int spid)
    {
        mWeeklyPlanDao = dao;
        selectedPlanID = spid;
        mSelectedUnassignedActivity = unassignedDailyActivities;
        mActivityToReassign = activity;

    }
    @Override
    protected Void doInBackground(Void... voids)
    {
        DailyActivity newActivity = new DailyActivity(selectedPlanID);
        newActivity.status = mActivityToReassign.status;
        newActivity.type = mActivityToReassign.type;
        newActivity.name = mActivityToReassign.name;
        newActivity.instructions = mActivityToReassign.instructions;
        mWeeklyPlanDao.addDailyActivity(newActivity);

        DailyActivity oldActivity = mWeeklyPlanDao.getDailyActivityByID(mSelectedUnassignedActivity.id);
        mWeeklyPlanDao.deleteDailyActivity(oldActivity);
        mWeeklyPlanDao.deleteDailyActivity(mActivityToReassign);


        return null;
    }
}
