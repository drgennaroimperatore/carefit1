package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.DataObjects.PastWeekRow;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PastWeekReader extends AsyncTask<Void, Void, List<PastWeekRow>> {


   private WeeklyPlanDao mDao;
    public PastWeekReader (WeeklyPlanDao dao)
    {
        mDao = dao;
    }
    @Override
    protected List<PastWeekRow> doInBackground(Void... voids) {
        List<PastWeekRow> pastWeekRows = new ArrayList<>();
        List<WeeklyPlan> plans = mDao.getWeeklyPlans();
        if(plans.isEmpty())
            return pastWeekRows;

        int i =0;
        while (!DateTimeAssist.isDateInCurrentWeek(plans.get(i).startDate))
        {

            WeeklyPlan plan = plans.get(i);

            PastWeekRow row = new PastWeekRow();
            Date[] dates = DateTimeAssist.getWeekDates(plan.startDate);
            String startDate = DateTimeAssist.convertDateToPreferredFormat(dates[0]);
            String endDate = DateTimeAssist.convertDateToPreferredFormat(dates[dates.length-1]);

            row.setmStartDate(startDate);
            row.setmEndDate(endDate);

            int completedCardios = mDao.getCompletedActivityCountByTypeAndWeeklyPlanID(plan.id, ExerciseTypes.CARDIO);
            int completedMuscle = mDao.getCompletedActivityCountByTypeAndWeeklyPlanID(plan.id, ExerciseTypes.MUSCLE);
            int completedOther = mDao.getCompletedActivityCountByTypeAndWeeklyPlanID(plan.id, ExerciseTypes.OTHER);

            row.setmCompletedCardios(completedCardios);
            row.setmCompletedMuscleBalances(completedMuscle);
            row.setmCompletedCompendiums(completedOther);
           i++;
           pastWeekRows.add(row);
        }

        return pastWeekRows;
    }
}
