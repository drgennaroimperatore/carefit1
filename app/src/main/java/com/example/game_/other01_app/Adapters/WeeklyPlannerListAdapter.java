package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_.other01_app.DataObjects.WeeklyPlannerObject;
import com.example.game_.other01_app.R;

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
        RecyclerView dailyRecyclerView = convertView.findViewById(R.id.weekly_planner_dailyactivity_recyclerView);
        WeeklyPlannerDailyActivityRecyclerViewAdapter adapter = new WeeklyPlannerDailyActivityRecyclerViewAdapter(mContext);
        dailyRecyclerView.setAdapter(adapter);


        return convertView;

    }
}