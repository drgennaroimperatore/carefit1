package com.example.game_.other01_app.Database.UsageDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/*1. Education analytics around

- Modules started

- Modules finished

- Time spent on each educational section

- Total time spent on all education sessions

- Successful planning of weeks activities?

2. Physical activities around

- Total number of activities started

- Total number of activities completed

- Time spent on each physical activity

- Total time spent on physical activities

- Types of exercises undertaken

o Sedentary behaviour

o Cardiovascular activity

o Muscle and balance activities

3. Communication around

- Total number of onwards shares

- Modality of sharing information (e.g. twitter, WhatsApp, Facebook etc)

- Interactions within the app (e.g. message boards or other members of the group)*/
@Database( entities = {ActivitiesStats.class},version = 1, exportSchema = false)
public abstract class UsageDB extends RoomDatabase {

    private static volatile UsageDB mInstance;

    public static UsageDB getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = Room.databaseBuilder(context,
                    UsageDB.class, "usageDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }
}
