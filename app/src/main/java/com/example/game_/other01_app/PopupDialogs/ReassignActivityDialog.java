package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.UnassignedDailyActivitiesReader;

import java.util.ArrayList;
import java.util.List;

public class ReassignActivityDialog extends Dialog {
    private Context mContext;
    public ReassignActivityDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light);
        mContext = context;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reassign);
        ListView availableDatesListView = findViewById(R.id.dialog_reassign_available_listview);
        List<UnassignedDailyActivities> unassignedDailyActivities = new ArrayList();
        try
        {
            unassignedDailyActivities =
                    new UnassignedDailyActivitiesReader(AppDatabase.getDatabase(mContext).weeklyPlanDao()).execute().get();
            ArrayAdapter<UnassignedDailyActivities> activitiesArrayAdapter = new ArrayAdapter<>(mContext,R.layout.dialog_reassign_available_listview_row,unassignedDailyActivities);
            availableDatesListView.setAdapter(activitiesArrayAdapter);
        } catch (Exception e)
        {

        }
    }
}
