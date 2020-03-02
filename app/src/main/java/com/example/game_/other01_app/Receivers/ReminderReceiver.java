package com.example.game_.other01_app.Receivers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import com.example.game_.other01_app.Activities.ExerciseListActivity;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.AlarmCreator;

import static android.content.ContentValues.TAG;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int icon = R.drawable.bicepcurls;
        CharSequence contentTitle = "CareFit Reminder";
        CharSequence contentText = "Time to do some exercises!";
        final int NOTIF_ID = 1234;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, ExerciseListActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        NotificationChannel notificationChannel = null;
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(icon)
                .setContentTitle(contentTitle)
                .setTicker(contentTitle)
                .setContentText(contentText)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(contentIntent);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("cid", "name",
                    NotificationManager.IMPORTANCE_HIGH);
            builder.setChannelId("cid");
            notificationChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    new AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
            .build());
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(NOTIF_ID, builder.build());

        int _id = intent.getIntExtra(AlarmCreator.ALARM_ID, 0);
        Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
        Log.d(TAG, "onReceive: id = " + _id );
        AlarmCreator.resetWeeklyAlarm(_id, context);
    }
}
