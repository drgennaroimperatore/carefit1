package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Fragments.ExerciseInstructionBoxFragment;
import com.example.game_.other01_app.R;

public class ExcerciseDescriptionDialog extends Dialog {
private Context mContext;
private Bundle mArgs;
private  WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;

    public ExcerciseDescriptionDialog(@NonNull Context context, Bundle args, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter) {
        super(context);
        mContext =context;
        mArgs =args;
        mAdapter = adapter;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_activity_description);
        super.onCreate(savedInstanceState);

        LinearLayout confirmationSection = findViewById(R.id.dialog_activity_description_add_confirmation_section);
        LinearLayout completionSection = findViewById(R.id.dialog_activity_description_add_completion_section);
        confirmationSection.setVisibility(View.GONE);
        completionSection.setVisibility(View.GONE);

        if(mArgs!=null)
        {
            boolean isAssigned = mArgs.getBoolean("isAssigned",false);
            ExerciseTypes activityType = ExerciseTypes.valueOf(mArgs.getString("type"));
            int pos= mArgs.getInt("pos");

            if(!isAssigned)
            {
                confirmationSection.setVisibility(View.VISIBLE);
                ImageView confirmInsertionImgView = findViewById(R.id.dialog_activity_description_add_to_plan_imgview);
                confirmInsertionImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.assignActivity(activityType,pos);
                        dismiss();
                    }
                });

                ImageView dismissDialogImgView = findViewById(R.id.dialog_activity_description_cancel_add_imgview);
                dismissDialogImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
            else
            {
                completionSection.setVisibility(View.VISIBLE);
            }
        }

    }


}
