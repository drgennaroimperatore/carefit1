package com.example.game_.other01_app.Utility;

import android.os.AsyncTask;
import android.service.media.MediaBrowserService;

import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.entities.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderReader extends AsyncTask<String, Void, List<Reminder>> {

    private ReminderDao reminderDao;
    public ReminderReader(ReminderDao dao)
    {
        reminderDao = dao;

    }

    @Override
    protected List<Reminder> doInBackground(String... reminders) {

        if(reminders[0]!=null)

        {
            ArrayList<Reminder> result = new ArrayList<>();
            result.add(reminderDao.findReminderByStrID(reminders[0]));

        }

        return reminderDao.getAllRemindersNotLive();
    }
}
