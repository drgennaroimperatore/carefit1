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

public class ReminderTimeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            new cancelAndSetAsyncTask().execute(context);
    }

    /**
     * Cancels and re-sets alarms when device time is changed
     */
    private static class cancelAndSetAsyncTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            AppDatabase db = AppDatabase.getDatabase(contexts[0]);
            ReminderDao reminderDao = db.reminderDao();
            List<Reminder> allReminders = reminderDao.getAllRemindersNotLive();
            for(Reminder current : allReminders){
                AlarmCreator.deleteReminder(current, contexts[0]);
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
