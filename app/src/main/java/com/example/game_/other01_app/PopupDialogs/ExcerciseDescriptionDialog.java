package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.Adapters.WeeklyPlannerDailyActivityRecyclerViewAdapter;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Fragments.ExerciseInstructionBoxFragment;
import com.example.game_.other01_app.R;

public class ExcerciseDescriptionDialog extends Dialog {
private Context mContext;
private Bundle mArgs;
private  WeeklyPlannerDailyActivityRecyclerViewAdapter mAdapter;
AddActivityDialog mAddActivityDialog;

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
            String myVideoYoutubeId = "cpr7qhexikY";
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

            youTubeWeb.loadUrl("https://www.youtube.com/embed/" + myVideoYoutubeId);
            DailyActivityStatus status = DailyActivityStatus.valueOf( mArgs.getString("status"));
            ExerciseTypes activityType = ExerciseTypes.valueOf(mArgs.getString("type"));
            int pos= mArgs.getInt("pos");

            if(status == DailyActivityStatus.NOT_ASSIGNED)
            {
                confirmationSection.setVisibility(View.VISIBLE);
                ImageView confirmInsertionImgView = findViewById(R.id.dialog_activity_description_add_to_plan_imgview);
                confirmInsertionImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.assignActivity(activityType,pos);
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
                /*    LinearLayout rescheduleSection = findViewById(R.id.dialog_activity_description_reschedule_section);
                    rescheduleSection.setVisibility(View.VISIBLE);
                    ImageView calendarButton = findViewById(R.id.dialog_activity_description_reschedule_section_open_rescheduler_imgView);
                    calendarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ReassignActivityDialog reassignActivityDialog = new ReassignActivityDialog(mContext);
                            reassignActivityDialog.show();
                        }

                    });
                    ImageView dismissRescheduler = findViewById(R.id.dialog_activity_description_reschedule_section_dismiss_imgView);
                    dismissRescheduler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dismiss();
                        }
                    }); */


                }// is past
                if(mArgs.getBoolean("isFuture"))
                {
                /*    LinearLayout reminderSection = findViewById(R.id.dialog_activity_description_add_notification_section);
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
                    });*/

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


                }//is future
            } // if is assigned

        }

    }

}
