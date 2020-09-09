package com.example.game_.other01_app.Utility;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;

public class CustomNotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        String strID = intent.getStringExtra("StrID");
        notificationManager.notify(notificationId, notification);

        ReminderDao dao =  AppDatabase.getDatabase(context).reminderDao();
        Reminder reminderToDelete = dao.findReminderByStrID(strID);
        dao.delete(reminderToDelete);

    }
}
