package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.CompletedDailyActivities;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;

import java.util.ArrayList;
import java.util.List;

public class CompletedDailyActivitiesReader extends AsyncTask <ExerciseTypes, Void, List<CompletedDailyActivities>> {

    private WeeklyPlanDao mDao;
    public CompletedDailyActivitiesReader(WeeklyPlanDao dao)
    {
        mDao = dao;
    }
    
    @Override
    protected List<CompletedDailyActivities> doInBackground(ExerciseTypes... voids) {
        List<CompletedDailyActivities>  completedDailyActivities = mDao.getAllCompletedActivities(voids[0]);
        return completedDailyActivities;
    }
}
