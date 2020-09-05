package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.DailyActivityPopulator;

import java.util.HashMap;

public class MuscleBalanceDialog extends Dialog implements View.OnClickListener {
    Bundle mArgs;
    WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
    Context mContext;
    AddActivityDialog mAddActivityDialog;
    CardView mSelectedCardView;
    CardView muscleSet1, muscleSet2, muscleSet3;
    int mSelectedIntensity = 0;


    TextView mLowIntensityHeader, mModerateIntensityHeader, mVigorousIntensityHeader;
    public MuscleBalanceDialog(@NonNull Context context, Bundle args, AddActivityDialog addActivityDialog, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter) {

        super(context,R.style.Theme_Design_Light);
        mArgs = args;
        mAdapter = adapter;
        mContext = context;
        mAddActivityDialog = addActivityDialog;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_muscle);

        HashMap<String, String> exContent = DailyActivityPopulator.generateMuscleAndBalanceContent();



        ImageView confirmButton = findViewById(R.id.dialog_add_muscle_confirm_imgview);
        confirmButton.setOnClickListener(this);

        ImageView closeButton = findViewById(R.id.dialog_add_muscle_close_imgview);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        muscleSet1 = findViewById(R.id.dialog_add_muscle_muscle_set1);
        mSelectedCardView = muscleSet1;
        muscleSet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView) view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);
                String key = "Muscle and Balance 1";
                mArgs.putString("Name",key);
                mArgs.putString("Description",exContent.get(key) );

            }
        });
        muscleSet2 = findViewById(R.id.dialog_add_muscle_muscle_set2);
        muscleSet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView) view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);
                String key = "Muscle and Balance 2";
                mArgs.putString("Name",key);
                mArgs.putString("Description",exContent.get(key) );
            }
        });
        muscleSet3 = findViewById(R.id.dialog_add_muscle_muscle_set3);
        muscleSet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView) view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);
                String key = "Muscle and Balance 3";
                mArgs.putString("Name",key);
                mArgs.putString("Description",exContent.get(key) );
            }
        });

        //deselectAll();
/*        CardView balanceSet1 = findViewById(R.id.dialog_add_muscle_balance_set1);
        CardView balanceSet2 = findViewById(R.id.dialog_add_muscle_balance_set2);
        CardView balanceSet3 = findViewById(R.id.dialog_add_muscle_balance_set3);*/

        mLowIntensityHeader = findViewById(R.id.dialog_muscle_low_header);
        mModerateIntensityHeader = findViewById(R.id.dialog_muscle_moderate_header);
        mVigorousIntensityHeader = findViewById(R.id.dialog_muscle_vigorous_header);

        mLowIntensityHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedIntensity=0;
                adjustIntensityHeaders();
            }
        });

        mModerateIntensityHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedIntensity=1;
                adjustIntensityHeaders();

            }
        });
        mVigorousIntensityHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedIntensity=2;
                adjustIntensityHeaders();

            }
        });


        adjustIntensityHeaders();
    }


    public void deselectAll()
    {
        muscleSet1.setCardBackgroundColor(Color.BLUE);
        muscleSet3.setCardBackgroundColor(Color.BLUE);
        muscleSet2.setCardBackgroundColor(Color.BLUE);
    }

    public void adjustIntensityHeaders()
    {

        switch (mSelectedIntensity)
        {
            case 0:
                mLowIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
                mModerateIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                mVigorousIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                break;
            case 1:
                mLowIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                mModerateIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
                mVigorousIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                break;
            case 2:
                mLowIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                mModerateIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                mVigorousIntensityHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        // to do implement different action based on cardview selected. For the moment just display the dialog
        if (!mArgs.containsKey("Name"))
            return;
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
