package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnassignedDailyActivitiesReader extends AsyncTask <Void, Void, List<UnassignedDailyActivities>> {

    private WeeklyPlanDao mDao;
    public UnassignedDailyActivitiesReader (WeeklyPlanDao dao)
    {
        mDao = dao;
    }
    
    @Override
    protected List<UnassignedDailyActivities> doInBackground(Void... voids) {
        List<UnassignedDailyActivities>  unassignedDailyActivities = mDao.getAllUnassignedActivities();
      List<UnassignedDailyActivities> returnValues = new ArrayList<>();

        for (UnassignedDailyActivities u: unassignedDailyActivities
             )
        {
            if(DateTimeAssist.isAfter(u.dayOfWeek))
            {
                returnValues.add(u);
            }

        }


        return returnValues;
    }
}
