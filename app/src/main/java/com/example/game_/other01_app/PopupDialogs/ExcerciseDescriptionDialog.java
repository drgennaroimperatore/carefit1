package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Fragments.ExerciseInstructionBoxFragment;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.QualtricsJSLink;
import com.example.game_.other01_app.Utility.YouTubeJSLink;

public class ExcerciseDescriptionDialog extends Dialog implements YouTubeJSLink.YouTubeTimer {
private Context mContext;
private Bundle mArgs;
private  WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
private final String [] mMuscleBalanceURLs =
        new String[]{"nsrHeSjp7UA","vC6b5zEpuZ4","4LaJ8hWoF4","7MRMl3Nt9z","X8kH_pFkDoI"};
private final String [] mCardioURLs =
            new String[]{"kB65Rp-IxA0","swLE64DXfNI","3nLPYSF74fY"};

AddActivityDialog mAddActivityDialog;
      String name =  null;
     String instructions = null;

     //timer stuff

    private Handler handler;
    public Handler getHandler() {
        return handler;
    }
    private long MilliTime, StartTime, TimeBuffer, UpdateTime = 0L;
    private int Seconds, Minutes, MilliSeconds;
    private TextView mTimerTV;
    private ImageView mStartBtn;
    private ImageView mStopButton;

    public ExcerciseDescriptionDialog(@NonNull Context context, AddActivityDialog addActivityDialog, Bundle args, WeeklyPlannerDailyActivityRecyclerViewAdapter adapter) {
        super(context,R.style.Theme_Design_Light);
        mContext =context;
        mArgs =args;
        mAdapter = adapter;
        mAddActivityDialog = addActivityDialog;

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
            int pos= mArgs.getInt("pos");
            TextView activityNameTV = findViewById(R.id.dialog_activity_desc_name);
            TextView activityDescTV = findViewById(R.id.dialog_activity_desc_description);

            if(mArgs.containsKey("Name")) {
                activityNameTV.setText(mArgs.getString("Name"));
                name = mArgs.getString("Name");
            }
            else
            {
                name =mAdapter.getActivity(pos).name;

            }

            if(mArgs.containsKey("Description")) {
                activityDescTV.setText(mArgs.getString("Description"));
                instructions =mArgs.getString("Description");
            }
            else
            {
             instructions = mAdapter.getActivity(pos).instructions;
            }


            String myVideoYoutubeId = "";
            ExerciseTypes activityType = ExerciseTypes.valueOf(mArgs.getString("type"));

            if(activityType.equals(ExerciseTypes.MUSCLE))
            {
                if(name.contains("1"))
                    myVideoYoutubeId = mMuscleBalanceURLs[0];
                if(name.contains("2"))
                    myVideoYoutubeId = mMuscleBalanceURLs[1];
                if(name.contains("3"))
                    myVideoYoutubeId = mMuscleBalanceURLs[2];
                if(name.contains("4"))
                    myVideoYoutubeId = mMuscleBalanceURLs[3];
            }



            WebView youTubeWeb = findViewById(R.id.dialog_activity_description_video);


            youTubeWeb.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });


            WebSettings webSettings = youTubeWeb.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            youTubeWeb.addJavascriptInterface(new YouTubeJSLink(youTubeWeb, this), "StrathAndroid");

            youTubeWeb.loadUrl("file:///android_asset/youtubeshow.html?id=" + myVideoYoutubeId);//add &tstart=... and &tend=... to specify a start and end time
            DailyActivityStatus status = DailyActivityStatus.valueOf( mArgs.getString("status"));


            if(activityType.equals(ExerciseTypes.OTHER))
                youTubeWeb.setVisibility(View.GONE);



            if(status == DailyActivityStatus.NOT_ASSIGNED)
            {
                confirmationSection.setVisibility(View.VISIBLE);
                ImageView confirmInsertionImgView = findViewById(R.id.dialog_activity_description_add_to_plan_imgview);
                confirmInsertionImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.assignActivity(name, instructions,activityType,pos);
                        mAddActivityDialog.dismiss();
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
            else if(status == DailyActivityStatus.ASSIGNED )
            {
                if(activityType.equals(ExerciseTypes.CARDIO))
                {
                    setupCardioSection();
                }

                activityNameTV.setText(mAdapter.getActivity(pos).name);
                activityDescTV.setText(mAdapter.getActivity(pos).instructions);
                if(mArgs.getBoolean("isToday")) {
                    completionSection.setVisibility(View.VISIBLE);

                    ImageView completedActivityImgview, partiallyCompletedActivtyImgView;
                    completedActivityImgview = findViewById(R.id.dialog_activity_description_completed_ex_imgview);
                    completedActivityImgview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAdapter.markActivityAsComplete(pos);
                            dismiss();

                        }
                    });
                    partiallyCompletedActivtyImgView = findViewById(R.id.dialog_activity_description_partially_completed_imgview);
                    partiallyCompletedActivtyImgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAdapter.markActivityAsPartialComplete(pos);
                            dismiss();
                        }
                    });

                    ImageView dontWantToCompleteImgView = findViewById(R.id.dialog_activity_description_dont_want_to_doimgview);
                    dontWantToCompleteImgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mAdapter.unassignActivity(pos);
                            dismiss();
                        }
                    });
                } // if is today

                if(mArgs.getBoolean("isPast"))
                {
                   LinearLayout rescheduleSection = findViewById(R.id.dialog_activity_description_reschedule_section);
                    rescheduleSection.setVisibility(View.VISIBLE);
                    ImageView calendarButton = findViewById(R.id.dialog_activity_description_reschedule_section_open_rescheduler_imgView);
                    calendarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ReassignActivityDialog reassignActivityDialog = new ReassignActivityDialog(mContext,mAdapter,mAdapter.getActivity(pos));
                            reassignActivityDialog.show();
                        }

                    });
                    ImageView dismissRescheduler = findViewById(R.id.dialog_activity_description_reschedule_section_dismiss_imgView);
                    dismissRescheduler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dismiss();
                        }
                    });


                }// is past
                if(mArgs.getBoolean("isFuture"))
                {
                   LinearLayout reminderSection = findViewById(R.id.dialog_activity_description_add_notification_section);
                    reminderSection.setVisibility(View.VISIBLE);


                    ImageView bellImageView = findViewById(R.id.dialog_activity_description_add_notification_section_bell_imgview);
                    bellImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SetReminderDialog setReminderDialog = new SetReminderDialog(mContext);
                            setReminderDialog.show();

                        }
                    });
                    ImageView dismissimgView = findViewById(R.id.dialog_activity_description_add_notification_section_dismiss_imgView);
                    dismissimgView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dismiss();
                        }
                    });


                }//is future
            } // if is assigned

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

    public void setupCardioSection()
    {
        mTimerTV = findViewById(R.id.dialog_add_cardio_timer_textView);
        mStartBtn = findViewById(R.id.dialog_add_cardio_start_timer_imageView);
        mStopButton = findViewById(R.id.dialog_add_cardio_stop_timer_imageView);
        LinearLayout cardioSection = findViewById(R.id.dialog_activity_description_cardio_section);
        cardioSection.setVisibility(View.VISIBLE);

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

            }
        });
    }

    @Override
    public void timeinfo(float time) {
        //Called from YouTube HTML about every 500 ms
        Log.d("DUNLOPDUNLOP", "got the time "+time);
        Toast.makeText(this.mContext,"t="+time,Toast.LENGTH_SHORT).show();
    }
}
