package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.AssistanceClasses.DateTimeAssist;
import com.example.game_.other01_app.DataObjects.WeeklyPlannerObject;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.R;

import java.util.ArrayList;
import java.util.Date;

public class WeeklyPlannerListAdapter extends ArrayAdapter<WeeklyPlannerObject> {

    Context mContext;

    public WeeklyPlannerListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mContext =context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.weekly_planner_row,null);
        TextView dayTV = convertView.findViewById(R.id.weekly_planner_row_day_tv);
        dayTV.setText(getItem(position).getmDay());
        TextView dateTV = convertView.findViewById(R.id.weekly_planner_row_date_tv);
        dateTV.setText(DateTimeAssist.convertDateToPreferredFormat(getItem(position).getmDailyPlan().dayOfWeek));
        TextView statusTV = convertView.findViewById(R.id.weekly_planner_row_status_tv);
        Date activityDate = getItem(position).getmDailyPlan().dayOfWeek;
        if(DateTimeAssist.isBefore(activityDate))
            statusTV.setText("PAST");
        if(DateTimeAssist.isDateToday(activityDate))
            statusTV.setText("TODAY");
        if(DateTimeAssist.isAfter(activityDate))
            statusTV.setText("FUTURE");

        RecyclerView dailyRecyclerView = convertView.findViewById(R.id.weekly_planner_dailyactivity_recyclerView);
        WeeklyPlannerDailyActivityRecyclerViewAdapter adapter =
                new WeeklyPlannerDailyActivityRecyclerViewAdapter(mContext,getItem(position).getmDailyPlan());
        dailyRecyclerView.setAdapter(adapter);

        return convertView;

    }
}
