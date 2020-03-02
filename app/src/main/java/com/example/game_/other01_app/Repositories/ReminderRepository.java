package com.example.game_.other01_app.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ReminderRepository {
    private final ReminderDao mReminderDao;
    private final LiveData<List<Reminder>> mAllReminders;

    public ReminderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mReminderDao = db.reminderDao();
        mAllReminders = mReminderDao.getAllReminders();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return mAllReminders;
    }

    public void insert(Reminder reminder) {
        new insertAsyncTask(mReminderDao).execute(reminder);
    }

    public void deleteReminder(Reminder reminder){
        new deleteReminderAsyncTask(mReminderDao).execute(reminder);
    }

    private static class insertAsyncTask extends AsyncTask<Reminder, Void, Void> {

        private final ReminderDao mReminderDao;

        insertAsyncTask(ReminderDao dao){
            mReminderDao = dao;
        }

        @Override
        protected Void doInBackground(final Reminder... reminders) {
            mReminderDao.insert(reminders[0]);
            return null;
        }
    }

    private static class deleteReminderAsyncTask extends AsyncTask<Reminder, Void, Void> {

        private final ReminderDao mReminderDao;

        deleteReminderAsyncTask(ReminderDao reminderDao){
            mReminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {
            mReminderDao.delete(reminders[0]);
            return null;
        }
    }
}
