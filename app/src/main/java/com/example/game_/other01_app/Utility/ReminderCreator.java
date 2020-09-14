package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;

import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;

public class ReminderCreator extends AsyncTask<Reminder, Void, Void> {

    private ReminderDao mReminderDao;
    public ReminderCreator(ReminderDao dao)
    {
        mReminderDao = dao;

    }

    @Override
    protected Void doInBackground(Reminder... reminders) {
        mReminderDao.insert(reminders[0]);
        return null;
    }








}