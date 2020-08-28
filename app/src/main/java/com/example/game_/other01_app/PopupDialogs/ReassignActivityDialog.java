package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;
import com.example.game_.other01_app.EventSystem.DatabaseEvents;
import com.example.game_.other01_app.EventSystem.CurrentReschedulerWatcher;
import com.example.game_.other01_app.EventSystem.FutureRescheduleWatcher;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.DailyActivityRescheduler;
import com.example.game_.other01_app.Utility.UnassignedDailyActivitiesReader;

import java.util.ArrayList;
import java.util.List;

public class ReassignActivityDialog extends Dialog {
    private Context mContext;
    private DailyActivity mActivityToReassign;
    private int mSelectedPlanID =0;
    UnassignedDailyActivities mSelectedUnassignedActivity;
    private WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
    public ReassignActivityDialog(@NonNull Context context, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter, DailyActivity activity) {
        super(context, R.style.Theme_AppCompat_Light);
        mContext = context;
        mActivityToReassign = activity;
        mAdapter =adapter;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reassign);
        ListView availableDatesListView = findViewById(R.id.dialog_reassign_available_listview);
        List<UnassignedDailyActivities> unassignedDailyActivities = new ArrayList();
        TextView selectedDateTv = findViewById(R.id.dialog_reassign_selected_date_textview);
        ImageView confirmButton = findViewById(R.id.dialog_reassign_available_confirm);
        try
        {
            unassignedDailyActivities =
                    new UnassignedDailyActivitiesReader(AppDatabase.getDatabase(mContext).weeklyPlanDao()).execute().get();
            ArrayAdapter<UnassignedDailyActivities> activitiesArrayAdapter = new ArrayAdapter<>(mContext,R.layout.dialog_reassign_available_listview_row,unassignedDailyActivities);
            availableDatesListView.setAdapter(activitiesArrayAdapter);

            availableDatesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    UnassignedDailyActivities unassignedDailyActivity = activitiesArrayAdapter.getItem(i);
                    mSelectedPlanID = unassignedDailyActivity.dailyPlanId;
                    selectedDateTv.setText(unassignedDailyActivity.toString());
                    mSelectedUnassignedActivity = unassignedDailyActivity;
                    confirmButton.setVisibility(View.VISIBLE);
                }
            });

            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  new DailyActivityRescheduler(AppDatabase.getDatabase(mContext).weeklyPlanDao(),mActivityToReassign,mSelectedUnassignedActivity,mSelectedPlanID).execute();
                    mAdapter.notifyDataSetChanged();
                   try {
                       CurrentReschedulerWatcher.getInstance().update(DatabaseEvents.EDUCATIONAL_TABLE_CREATION_STARTED);
                       FutureRescheduleWatcher.getInstance().update(DatabaseEvents.EDUCATIONAL_TABLE_CREATION_STARTED);
                   } catch (Exception e)
                   {

                   }
                    dismiss();
                }
            });

        } catch (Exception e)
        {

        }
    }
}
