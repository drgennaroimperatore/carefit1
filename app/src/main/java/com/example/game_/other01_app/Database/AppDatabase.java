package com.example.game_.other01_app.Database;


import android.content.Context;
import android.os.AsyncTask;

import com.example.game_.other01_app.Database.converters.Converter;
import com.example.game_.other01_app.Database.daos.CategoriesDao;
import com.example.game_.other01_app.Database.daos.CompendiumsDao;
import com.example.game_.other01_app.Database.daos.EducationalDao;
import com.example.game_.other01_app.Database.daos.ExerciseDao;
import com.example.game_.other01_app.Database.daos.ReminderDao;
import com.example.game_.other01_app.Database.daos.TimeSetDao;
import com.example.game_.other01_app.Database.daos.UserDao;
import com.example.game_.other01_app.Database.daos.WeeklyPlanDao;
import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;
import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.EducationalList;
import com.example.game_.other01_app.Database.entities.EducationalListContent;
import com.example.game_.other01_app.Database.entities.EducationalListContentResource;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.Database.entities.TimeSet;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;
import com.example.game_.other01_app.EventSystem.DashboardDatabaseWatcher;
import com.example.game_.other01_app.EventSystem.DatabaseEvents;
import com.example.game_.other01_app.EventSystem.Watched;
import com.example.game_.other01_app.EventSystem.Watcher;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//A holder class that uses annotation to define the list of entities and
//the database version. This class content defines the list of DAOs
@Database( entities = {Exercise.class,
        TimeSet.class, User.class,
        Category.class, Reminder.class,
        WeeklyPlan.class, DailyPlan.class,
        DailyActivity.class,
        CompendiumActivities.class,
        EducationalList.class,
        EducationalListContent.class,
        EducationalListContentResource.class},  version = 11, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    protected AppDatabase() {
        mDatabaseWatcher = null;
    }

    public abstract ExerciseDao exerciseDao();
    public abstract UserDao userDao();
    public abstract CategoriesDao categoriesDao();
    public abstract ReminderDao reminderDao();
    public abstract TimeSetDao timeSetDao();
    public abstract CompendiumsDao compendiumsDao();
    public abstract WeeklyPlanDao weeklyPlanDao();
    public abstract EducationalDao educationalDao();

    public synchronized static AppDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addMigrations(MIGRATION_8_9,MIGRATION_9_10,MIGRATION_10_11)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE OTHER01Reminders");
            database.execSQL
                    /*Expected:
                    TableInfo{name='RemindersV2', columns={notificationID=Column{name='notificationID', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, hour=Column{name='hour', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, day=Column{name='day', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, minute=Column{name='minute', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}}, foreignKeys=[], indices=[]}
   found:
    TableInfo{name='RemindersV2', columns={hour=Column{name='hour', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, notificationID=Column{name='notificationID', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, id=Column{name='id', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=1}, day=Column{name='day', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, minute=Column{name='minute', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}}, foreignKeys=[], indices=[]}
    */

                    ("CREATE TABLE RemindersV2 ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, day INTEGER NOT NULL, hour INTEGER NOT NULL, minute INTEGER NOT NULL, notificationID INTEGER NOT NULL)");

        }
    };

    static final Migration MIGRATION_9_10 = new Migration(9,10) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE RemindersV2 ADD COLUMN strReminderID TEXT");

        }
    };

    static final Migration MIGRATION_10_11= new Migration(10,11) {

        /*Expected:
    TableInfo{name='DailyActivity', columns={instructions=Column{name='instructions', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, dailyPlanId=Column{name='dailyPlanId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, mins=Column{name='mins', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, secs=Column{name='secs', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, millisecs=Column{name='millisecs', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, name=Column{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, type=Column{name='type', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, status=Column{name='status', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}}, foreignKeys=[ForeignKey{referenceTable='DailyPlan', onDelete='CASCADE', onUpdate='NO ACTION', columnNames=[dailyPlanId], referenceColumnNames=[id]}], indices=[]}
     Found:
    TableInfo{name='DailyActivity', columns={instructions=Column{name='instructions', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, dailyPlanId=Column{name='dailyPlanId', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, mins=Column{name='mins', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, milliSecs=Column{name='milliSecs', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, secs=Column{name='secs', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0}, name=Column{name='name', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1}, type=Column{name='type', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}, status=Column{name='status', type='TEXT', affinity='2', notNull=false, primaryKeyPosition=0}}, foreignKeys=[ForeignKey{referenceTable='DailyPlan', onDelete='CASCADE', onUpdate='NO ACTION', columnNames=[dailyPlanId], referenceColumnNames=[id]}], indices=[]}
    */

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE DailyActivity ADD COLUMN mins INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE DailyActivity ADD COLUMN secs INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE DailyActivity ADD COLUMN millisecs INTEGER NOT NULL DEFAULT 0");
        }
    };
    protected Watcher mDatabaseWatcher = null;
    public void addDatabaseWatcher(Watcher watcher)
    {
        mDatabaseWatcher = watcher;
    }
    public Watcher getDatabaseWatcher()
    {
        return mDatabaseWatcher;
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
                  PopulateEducationalListAndContent eduPopulator =
                          new PopulateEducationalListAndContent(INSTANCE);
                  eduPopulator.attachWatcher(INSTANCE.getDatabaseWatcher());
                  eduPopulator.execute();
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

    private static class PopulateEducationalListAndContent extends AsyncTask<Void, Void, Void>
    {
        private final EducationalDao mEducationalDao;
        private final Watched mWatched;


        @Override
        protected void onPreExecute() {

         //   mWatched.notifyWatchers(DatabaseEvents.EDUCATIONAL_TABLE_CREATION_STARTED);

        }

        private PopulateEducationalListAndContent(AppDatabase db) {
            this.mEducationalDao = db.educationalDao();
            mWatched = new Watched();

        }

        public void attachWatcher(Watcher watcher)
        {
            mWatched.addWatcher(watcher);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mEducationalDao.insertEducationalListElements(EducationalList.populateEducationalList());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
      //      mWatched.notifyWatchers(DatabaseEvents.EDUCATIONAL_TABLE_CREATION_ENDED);
        }
    }
}
