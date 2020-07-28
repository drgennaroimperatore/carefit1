package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.R;

public class MuscleBalanceDialog extends Dialog implements View.OnClickListener {
    Bundle mArgs;
    WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
    Context mContext;
    AddActivityDialog mAddActivityDialog;
    public MuscleBalanceDialog(@NonNull Context context, Bundle args, AddActivityDialog addActivityDialog, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter) {

        super(context);
        mArgs = args;
        mAdapter = adapter;
        mContext = context;
        mAddActivityDialog = addActivityDialog;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_muscle);
        CardView muscleSet1 = findViewById(R.id.dialog_add_muscle_muscle_set1);
        muscleSet1.setOnClickListener(this);
        CardView muscleSet2 = findViewById(R.id.dialog_add_muscle_muscle_set2);
        muscleSet2.setOnClickListener(this);
        CardView muscleSet3 = findViewById(R.id.dialog_add_muscle_muscle_set3);
        muscleSet3.setOnClickListener(this);

/*        CardView balanceSet1 = findViewById(R.id.dialog_add_muscle_balance_set1);
        CardView balanceSet2 = findViewById(R.id.dialog_add_muscle_balance_set2);
        CardView balanceSet3 = findViewById(R.id.dialog_add_muscle_balance_set3);*/




    }

    @Override
    public void onClick(View view) {
        // to do implement different action based on cardview selected. For the moment just display the dialog
        ExcerciseDescriptionDialog edd = new ExcerciseDescriptionDialog(mContext, mAddActivityDialog, mArgs ,mAdapter);
        edd.show();
        edd.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dismiss();
            }
        });


    }
}
