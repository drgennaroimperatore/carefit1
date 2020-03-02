package com.example.game_.other01_app.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.ViewModels.AlarmCreator;

import java.text.ParseException;
import java.util.List;

public class ReminderBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            //Reconfigure alarms on reboot.
            new reactivateAlarmsAsyncTask().execute(context);
        }
    }

    /**
     * Re-activates alarms when device re-boots
     */
    private static class reactivateAlarmsAsyncTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            AppDatabase db = AppDatabase.getDatabase(contexts[0]);
            ReminderDao reminderDao = db.reminderDao();
            List<Reminder> allReminders = reminderDao.getAllRemindersNotLive();
            for (Reminder current : allReminders){
                try {
                    AlarmCreator.createReminder(current, contexts[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}
