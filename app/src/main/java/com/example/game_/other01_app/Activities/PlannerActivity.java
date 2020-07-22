package com.example.game_.other01_app.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.game_.other01_app.Adapters.WeeklyPlannerListAdapter;
import com.example.game_.other01_app.DataObjects.WeeklyPlannerObject;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.R;

public class PlannerActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_planner);

        ListView weeklyListView = findViewById(R.id.weekly_planner_listVoew);
        WeeklyPlannerListAdapter adapter = new WeeklyPlannerListAdapter(this,0);
        adapter.add(new WeeklyPlannerObject("Mon", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Tue", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Wed", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Thu", new DailyActivity()));
        adapter.add(new WeeklyPlannerObject("Fri", new DailyActivity()));
        weeklyListView.setAdapter(adapter);
    }
}
