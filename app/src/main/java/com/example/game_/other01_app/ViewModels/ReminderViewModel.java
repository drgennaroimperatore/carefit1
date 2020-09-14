package com.example.game_.other01_app.ViewModels;

import android.app.Application;

import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.Repositories.ReminderRepository;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ReminderViewModel extends AndroidViewModel {

    //private final ReminderRepository mRepository;
   // private final LiveData<List<Reminder>> mAllRemidners;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
       /* mRepository = new ReminderRepository(application);
        mAllRemidners = mRepository.getAllReminders();*/
    }

    /**
     * Deletes Reminder from the AlarmManager and from the Database
     * @param reminder
     */
    public void deleteReminder(Reminder reminder){
        AlarmCreator.deleteReminder(reminder, getApplication());
        //mRepository.deleteReminder(reminder);
    }

    //public LiveData<List<Reminder>> getAllRemidners() { return mAllRemidners; }

    /**
     * Inserts a new Reminder into the AlarmManager and into the Database
     * @param reminder
     */
    public void insert(Reminder reminder) {
        try {
            AlarmCreator.createReminder(reminder, getApplication());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //mRepository.insert(reminder); }
}}
