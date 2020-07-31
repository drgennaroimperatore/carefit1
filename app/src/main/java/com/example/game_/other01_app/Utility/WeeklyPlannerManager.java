package com.example.game_.other01_app.Utility;

import android.content.Context;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

public class WeeklyPlannerManager
{
    private Context mContext;
    private static WeeklyPlannerManager mInstance;

    private WeeklyPlannerManager(Context context)
    {

        mContext = context;
    }

    public static WeeklyPlannerManager getInstance(Context context)
    {
        if (mInstance ==null)
        {
            mInstance = new WeeklyPlannerManager(context);
        }
        return mInstance;
    }

    public WeeklyPlan getOrCreateCurrentWeek()
    {
        return null;
    }
}
