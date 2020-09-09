package com.example.game_.other01_app.PopupDialogs;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.game_.other01_app.Activities.PlannerActivity;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.CustomNotificationPublisher;
import com.example.game_.other01_app.Utility.ReminderCreator;

import java.util.Calendar;
import java.util.Date;

public class SetReminderDialog extends Dialog {

    long mWhen = 0;

    public SetReminderDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light);
        setContentView(R.layout.dialog_set_reminder);

        Spinner daysBeforeSpinner = findViewById(R.id.dialog_set_reminder_days_before_spinner);
        String days[] = {"0", "1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.days_spinner_row, days);
        daysBeforeSpinner.setAdapter(arrayAdapter);

        ImageView setReminder = findViewById(R.id.dialog_set_reminder_confirm);
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNotification();
                dismiss();
            }
        });

        ImageView dismiss = findViewById(R.id.dialog_set_reminder_dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        TimePicker timePicked = findViewById(R.id.dialog_set_remider_timepicker);
        timePicked.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Calendar notificationCalendar = Calendar.getInstance();
                notificationCalendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                notificationCalendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                mWhen = notificationCalendar.getTimeInMillis();
            }
        });


    }

    public void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "11")
                .setSmallIcon(R.drawable.ic_error)
                .setContentTitle("CarefitReminder")
                .setContentText("This is a reminder from carefit")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)/*.setWhen(mWhen)*/;



        int notificationId = 11;


        Intent intent = new Intent(getContext(), PlannerActivity.class);
        PendingIntent activity = PendingIntent.getActivity(getContext(), notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(getContext(), CustomNotificationPublisher.class);
        notificationIntent.putExtra(CustomNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(CustomNotificationPublisher.NOTIFICATION, notification);

        Date dat = new Date();//initializes to now
        Calendar cal_now = Calendar.getInstance();
        if(mWhen==0)
            mWhen = cal_now.getTimeInMillis();
        notificationIntent.putExtra("StrID", String.valueOf(mWhen));

        Reminder  reminder = new Reminder();
        cal_now.setTimeInMillis(mWhen);
        reminder.setDay(cal_now.getTime());
        reminder.setStrReminderID(String.valueOf(mWhen));
        reminder.setHour(cal_now.get(Calendar.HOUR_OF_DAY));
        reminder.setMinute(cal_now.get(Calendar.MINUTE));

        //new ReminderCreator(AppDatabase.getDatabase(getContext()).reminderDao()).execute(reminder);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);



            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);


            alarmManager.set(AlarmManager.RTC, mWhen, pendingIntent);

        }
    }

