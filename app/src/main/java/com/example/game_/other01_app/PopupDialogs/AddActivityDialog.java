package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaDataSource;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
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

        final AddActivityDialog addActivityDialog = this;

       // mAddBalance = findViewById(R.id.weekly_planner_dailyactivity_addBalance_button);
        mAddCardio = findViewById(R.id.weekly_planner_dailyactivity_addCardio_button);
        mAddMuscle = findViewById(R.id.weekly_planner_dailyactivity_addMuscle_button);
        mAddOther = findViewById(R.id.weekly_planner_dailyactivity_addOther_button);

        Bundle dialogArgs = new Bundle();
        dialogArgs.putString("status", DailyActivityStatus.NOT_ASSIGNED.toString());
        dialogArgs.putInt("pos", mActivityPosition);

       mAddCardio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             /*  mWeeklyPlannerDARVAdapter.assignActivity(ExerciseTypes.CARDIO, mActivityPosition);
               dismiss();*/




             /*ExcerciseDescriptionDialog Edd = new ExcerciseDescriptionDialog(mContext,dialogArgs);
             Edd.show();*/
           }
       });

       mAddMuscle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             /*  mWeeklyPlannerDARVAdapter.assignActivity(ExerciseTypes.MUSCLE, mActivityPosition);
               dismiss();*/

               dialogArgs.putString("type",ExerciseTypes.MUSCLE.toString());
             MuscleBalanceDialog muscleBalanceDialog = new MuscleBalanceDialog(mContext, dialogArgs,addActivityDialog,mWeeklyPlannerDARVAdapter);
             muscleBalanceDialog.show();
           }
       });

       mAddOther.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               CompendiumActivitiesDialog compendiumActivitiesDialog = new CompendiumActivitiesDialog(mContext);
               compendiumActivitiesDialog.show();

               /* mWeeklyPlannerDARVAdapter.assignActivity(ExerciseTypes.OTHER, mActivityPosition);
               dismiss();*/
           }
       });
    }
}
