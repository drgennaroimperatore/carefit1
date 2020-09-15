package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.R;

public class CardioActivityDialog extends Dialog {

    private ImageView mConfirmButton;
    private int mSelectedIntensity;
    private CardView mSelectedCardView;
    private CardView cardioEx1, cardioEx2, cardioEx3;
    TextView mLowIntensityHeader, mModerateIntensityHeader, mVigorousIntensityHeader;
    WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
    AddActivityDialog mAddActivityDialog;
    private Context mContext;
    private Bundle mArgs;


    public CardioActivityDialog(@NonNull Context context, Bundle args, AddActivityDialog addActivityDialog,
                                WeeklyPlannerDailyActivityRecyclerViewAdapter weeklyPlannerDailyActivityRecyclerViewAdapter) {
        super(context,R.style.Theme_Design_Light);
        mAdapter = weeklyPlannerDailyActivityRecyclerViewAdapter;
        mAddActivityDialog = addActivityDialog;
        mContext = context;
        mArgs = args;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_cardio);

        mConfirmButton = findViewById(R.id.dialog_add_cardio_confirm_imgview);

        cardioEx1 = findViewById(R.id.dialog_add_cardio_cardio_set1);
        cardioEx2 = findViewById(R.id.dialog_add_cardio_muscle_set2);
        cardioEx3 = findViewById(R.id.dialog_add_cardio_cardio_set3);
        mSelectedCardView = cardioEx1;
        mConfirmButton.setVisibility(View.VISIBLE);

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to do implement different action based on cardview selected. For the moment just display the dialog
                ExcerciseDescriptionDialog edd = new ExcerciseDescriptionDialog(mContext, mAddActivityDialog, mArgs, mAdapter);
                edd.show();
                edd.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        dismiss();
                    }
                });

            }  });


        cardioEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);

            }
        });
        cardioEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);

            }
        });
        cardioEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectAll();
                mSelectedCardView = (CardView)view;
                mSelectedCardView.setCardBackgroundColor(Color.WHITE);

            }
        });

        //mConfirmButton.setVisibility(View.INVISIBLE);

        mLowIntensityHeader = findViewById(R.id.dialog_cardio_low_header);
        mModerateIntensityHeader = findViewById(R.id.dialog_cardio_moderate_header);
        mVigorousIntensityHeader = findViewById(R.id.dialog_cardio_vigorous_header);

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

        /*SeekBar intensitySeekBar = findViewById(R.id.dialog_cardio_intensity_seekbar);
        intensitySeekBar.setProgress(5);
        intensitySeekBar.setMax(100);

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
*/



    }

    private void deselectAll()
    {
        cardioEx1.setCardBackgroundColor(Color.BLUE);
        cardioEx2.setCardBackgroundColor(Color.BLUE);
        cardioEx3.setCardBackgroundColor(Color.BLUE);
    }

    private void adjustIntensityHeaders()
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


}