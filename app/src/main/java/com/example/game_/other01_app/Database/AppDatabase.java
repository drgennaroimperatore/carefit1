package com.example.game_.other01_app.Database;


import android.content.Context;
import android.os.AsyncTask;

import com.example.game_.other01_app.Database.converters.Converter;
import com.example.game_.other01_app.Database.daos.CategoriesDao;
import com.example.game_.other01_app.Database.daos.CompendiumsDao;
import com.example.game_.other01_app.Database.daos.ExerciseDao;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.daos.TimeSetDao;
import com.example.game_.other01_app.Database.daos.UserDao;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

//A holder class that uses annotation to define the list of entities and
//the database version. This class content defines the list of DAOs
@Database( entities = {Exercise.class, TimeSet.class, User.class,
        Category.class, Reminder.class, WeeklyPlan.class, DailyPlan.class,
        CompendiumActivities.class},  version = 5, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ExerciseDao exerciseDao();
    public abstract UserDao userDao();
    public abstract CategoriesDao categoriesDao();
    public abstract ReminderDao reminderDao();
    public abstract TimeSetDao timeSetDao();
    public abstract CompendiumsDao compendiumsDao();

    public synchronized static AppDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                    new PopulateExerciseTableAsync(INSTANCE).execute();
                    new PopulateTimeSetTableAsync(INSTANCE).execute();
                    new PopulateUserWithDefaultsAsync(INSTANCE).execute();
                    new PopulateCompendiumActivitiesAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CategoriesDao mCategoriesDao;

        PopulateDbAsync(AppDatabase db) {
            mCategoriesDao = db.categoriesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mCategoriesDao.deleteAll();
            mCategoriesDao.insertAll(Category.populateData());
            return null;
        }
    }

    private static class PopulateExerciseTableAsync extends AsyncTask<Void, Void, Void> {

        private final ExerciseDao mExerciseDao;

        PopulateExerciseTableAsync(AppDatabase db) {
            mExerciseDao = db.exerciseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mExerciseDao.deleteAll();
            mExerciseDao.insertAll(Exercise.populateData());
            return null;
        }
    }

    private static class PopulateTimeSetTableAsync extends AsyncTask<Void, Void, Void> {

        private final TimeSetDao mTimeSetDao;

        PopulateTimeSetTableAsync(AppDatabase db) {
            mTimeSetDao = db.timeSetDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mTimeSetDao.deleteAll();
            mTimeSetDao.insertAll(TimeSet.populateData(Exercise.populateData()));
            return null;
        }
    }

    private static class PopulateUserWithDefaultsAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mUserDao;

        private PopulateUserWithDefaultsAsync(AppDatabase db) {
            this.mUserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // mUserDao.deleteAll();
            mUserDao.insert(new User(0, "The user", "Carer", true,
           System.currentTimeMillis(), 0,
           0, "low",
                    "low", "low",
           0));
            return null;
        }
    }

    private static class PopulateCompendiumActivitiesAsync extends AsyncTask <Void, Void, Void>
    {
        private final CompendiumsDao mCompendiumsDao;

        private PopulateCompendiumActivitiesAsync(AppDatabase db) {
            this.mCompendiumsDao = db.compendiumsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mCompendiumsDao.insertAll(CompendiumActivities.populateTable());
            return null;
        }
    }
}
