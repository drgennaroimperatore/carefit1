package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.ProgressOverviewDialogAdapter;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.CompletedDailyActivities;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.CompletedDailyActivitiesReader;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ProgressOverviewDialog extends Dialog {

    Bundle mArgs;
    Context mContext;
    public ProgressOverviewDialog(@NonNull Context context, Bundle args) {
        super(context, R.style.Theme_AppCompat_Light);
        setContentView(R.layout.dialog_progress_overview);
        mArgs = args;
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeeklyPlanDao dao = AppDatabase.getDatabase(mContext).weeklyPlanDao();

        ExerciseTypes type = ExerciseTypes.valueOf( mArgs.getString("type"));

        ListView listView = findViewById(R.id.dialog_progress_overview_list);

        try {
            ArrayList<CompletedDailyActivities> othCompleted = (ArrayList<CompletedDailyActivities>) new CompletedDailyActivitiesReader(dao).execute(type).get();
            ProgressOverviewDialogAdapter otherAdapter = new ProgressOverviewDialogAdapter(mContext, 0, othCompleted);
            listView.setAdapter(otherAdapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (type)
        {
            case OTHER:
                break;
            case CARDIO:
                break;
            case MUSCLE:
                break;
        }


    }
}
