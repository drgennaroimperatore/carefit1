package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.game_.other01_app.R;

import java.util.Calendar;

public class SetReminderDialog extends Dialog {

    long mWhen =0;
    public SetReminderDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light);
        setContentView(R.layout.dialog_set_reminder);

        Spinner daysBeforeSpinner = findViewById(R.id.dialog_set_reminder_days_before_spinner);
        String days[] = {"0","1","2","3","4","5","6","7"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.days_spinner_row,days);
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
                 notificationCalendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                 notificationCalendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                 mWhen = notificationCalendar.getTimeInMillis();
             }
         });


    }

    public void createNotification ()
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "11")
                .setSmallIcon(R.drawable.ic_error)
                .setContentTitle("CarefitReminder")
                .setContentText("This is a reminder from carefit")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)/*.setWhen(mWhen)*/;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(11, builder.build());
    }
}
