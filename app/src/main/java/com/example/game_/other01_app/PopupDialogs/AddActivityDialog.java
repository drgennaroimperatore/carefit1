package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.R;

public class AddActivityDialog extends Dialog {
    private int mActivityPosition;
    private WeeklyPlannerDailyActivityRecyclerViewAdapter mWeeklyPlannerDARVAdapter;
    private Context mContext;
    public AddActivityDialog(@NonNull Context context, int position, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter) {
        super(context);
        mContext = context;
        mActivityPosition = position;
        mWeeklyPlannerDARVAdapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_activity);

        Button mAddCardio, mAddBalance, mAddMuscle, mAddOther;

        mAddBalance = findViewById(R.id.weekly_planner_dailyactivity_addBalance_button);
        mAddCardio = findViewById(R.id.weekly_planner_dailyactivity_addCardio_button);
        mAddMuscle = findViewById(R.id.weekly_planner_dailyactivity_addMuscle_button);
        mAddOther = findViewById(R.id.weekly_planner_dailyactivity_addOther_button);

        mAddBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MuscleBalanceDialog dialog = new MuscleBalanceDialog(mContext);
                dialog.show();
            }
        });
    }
}
