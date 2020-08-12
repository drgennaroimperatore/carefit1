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
                mSelectedCardView= (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);

            }
        });
         muscleSet2 = findViewById(R.id.dialog_add_muscle_muscle_set2);
        muscleSet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView= (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);
            }
        });
        muscleSet3 = findViewById(R.id.dialog_add_muscle_muscle_set3);
        muscleSet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               deselectAll();
                mSelectedCardView= (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);
            }
        });

        //deselectAll();
/*        CardView balanceSet1 = findViewById(R.id.dialog_add_muscle_balance_set1);
        CardView balanceSet2 = findViewById(R.id.dialog_add_muscle_balance_set2);
        CardView balanceSet3 = findViewById(R.id.dialog_add_muscle_balance_set3);*/

        mLowIntensityHeader = findViewById(R.id.dialog_muscle_low_header);
        mModerateIntensityHeader = findViewById(R.id.dialog_muscle_moderate_header);
        mVigorousIntensityHeader = findViewById(R.id.dialog_muscle_vigorous_header);

       adjustIntensityHeaders();

        SeekBar intensitySeekBar = findViewById(R.id.dialog_muscle_intensity_seekbar);
        intensitySeekBar.setMax(100);
        intensitySeekBar.setProgress(5);
        intensitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress= seekBar.getProgress();
                if(progress>=0 && progress<33) {
                    seekBar.setProgress(5);
                    mSelectedIntensity=0;
                }
                if(progress>=33 && progress<=50) {
                    seekBar.setProgress(50);
                    mSelectedIntensity=1;
                }
                if(progress>50) {
                    seekBar.setProgress(100);
                    mSelectedIntensity=2;
                }
              adjustIntensityHeaders();

            }
        });



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
