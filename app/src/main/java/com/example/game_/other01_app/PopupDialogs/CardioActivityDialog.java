package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
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

import com.example.game_.other01_app.R;

public class CardioActivityDialog extends Dialog {

    private long MilliTime, StartTime, TimeBuffer, UpdateTime = 0L;
    private int Seconds, Minutes, MilliSeconds;
    private TextView mTimerTV;
    private ImageView mStartBtn;
    private ImageView mStopButton;
    private ImageView mConfirmButton;
    private int mSelectedIntensity;
    private CardView mSelectedCardView;
    private CardView cardioEx1, cardioEx2, cardioEx3;
    TextView mLowIntensityHeader, mModerateIntensityHeader, mVigorousIntensityHeader;

    public Handler getHandler() {
        return handler;
    }

    private Handler handler;

    public CardioActivityDialog(@NonNull Context context) {
        super(context,R.style.Theme_Design_Light);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_cardio);
        mTimerTV = findViewById(R.id.dialog_add_cardio_timer_textView);
        mStartBtn = findViewById(R.id.dialog_add_cardio_start_timer_imageView);
        mStopButton = findViewById(R.id.dialog_add_cardio_stop_timer_imageView);
        mConfirmButton = findViewById(R.id.dialog_add_cardio_confirm_imgview);

        cardioEx1 = findViewById(R.id.dialog_add_cardio_cardio_set1);
        cardioEx2 = findViewById(R.id.dialog_add_cardio_muscle_set2);
        cardioEx3 = findViewById(R.id.dialog_add_cardio_cardio_set3);
        mSelectedCardView = cardioEx1;


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

        mConfirmButton.setVisibility(View.INVISIBLE);

        mLowIntensityHeader = findViewById(R.id.dialog_cardio_low_header);
        mModerateIntensityHeader = findViewById(R.id.dialog_cardio_moderate_header);
        mVigorousIntensityHeader = findViewById(R.id.dialog_cardio_vigorous_header);

        adjustIntensityHeaders();

        SeekBar intensitySeekBar = findViewById(R.id.dialog_cardio_intensity_seekbar);
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

        handler = new Handler();

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                TimeBuffer=0;

            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuffer += MilliTime;
                handler.removeCallbacks(runnable);
                mConfirmButton.setVisibility(View.VISIBLE);
            }


        });


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

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MilliTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuffer + MilliTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            mTimerTV.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };
}