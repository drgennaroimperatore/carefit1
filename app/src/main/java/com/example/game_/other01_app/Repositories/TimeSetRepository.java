package com.example.game_.other01_app.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.game_.other01_app.NonDBObjects.MiniTimeSet;
import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.TimeSetDao;
import com.example.game_.other01_app.Database.entities.TimeSet;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class TimeSetRepository {

    private final TimeSetDao mTimeSetDao;
    private final LiveData<List<TimeSet>> mAllTimeSets;

    public TimeSetRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mTimeSetDao = db.timeSetDao();
        mAllTimeSets = mTimeSetDao.getAll();
    }

    public void createOrUpdateTimeSet(TimeSet timeSet) {
        new createTimeSetAsyncTask(mTimeSetDao).execute(timeSet);
    }

    public LiveData<List<TimeSet>> getTimeSets() {
        return mAllTimeSets;
    }

    public void updateTimeSet(MiniTimeSet miniTimeSet) {
        new updateTimeSetAsyncTask(mTimeSetDao).execute(miniTimeSet);
    }

    public void resetAllRecentTimes() {
        new resetAllRecentTimesAsyncTask(mTimeSetDao).execute();
    }

    public void postMessageAdmin(List<TimeSet> timeSetsForBestUpdate) {
        new postMessageAdminAsyncTask(mTimeSetDao).execute(timeSetsForBestUpdate);
    }

    public List<TimeSet> getTimeSetsNotLive() throws ExecutionException, InterruptedException {
        return new getTimeSetsNotLiveAsyncTask(mTimeSetDao).execute().get();
    }

    private static class createTimeSetAsyncTask extends AsyncTask<TimeSet,Void,Void>{

        private final TimeSetDao mTimeSetDao;

        createTimeSetAsyncTask(TimeSetDao mTimeSetDao) {
            this.mTimeSetDao = mTimeSetDao;
        }

        @Override
        protected Void doInBackground(TimeSet... timeSets) {
            mTimeSetDao.insertTimeSet(timeSets[0]);
            return null;
        }
    }

    private static class updateTimeSetAsyncTask extends AsyncTask<MiniTimeSet, Void, Void>{

        private final TimeSetDao mTimeSetDao;

        updateTimeSetAsyncTask(TimeSetDao mTimeSetDao) {
            this.mTimeSetDao = mTimeSetDao;
        }

        @Override
        protected Void doInBackground(MiniTimeSet... timeSets) {
            mTimeSetDao.updateTimeSet(timeSets[0].getExerciseName(),
                    timeSets[0].getNewTodaysTime(),
                    timeSets[0].getNewLastAccessed());
            return null;
        }
    }


    private static class resetAllRecentTimesAsyncTask extends AsyncTask<Void, Void, Void> {

        private final TimeSetDao mTimeSetDao;

        public resetAllRecentTimesAsyncTask(TimeSetDao mTimeSetDao) {
            this.mTimeSetDao = mTimeSetDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mTimeSetDao.resetAllRecentTimes();
            return null;
        }
    }

    private static class postMessageAdminAsyncTask extends AsyncTask<List<TimeSet>, Void, Void>{

        private final TimeSetDao mTimeSetDao;

        public postMessageAdminAsyncTask(TimeSetDao mTimeSetDao) {
            this.mTimeSetDao = mTimeSetDao;
        }

        @Override
        protected Void doInBackground(List<TimeSet>... lists) {
            for (TimeSet timeSet : lists[0]){
                mTimeSetDao.updateBestTime(timeSet.getExercisename(), timeSet.getRecentTime());
            }
            mTimeSetDao.resetAllRecentTimes();
            return null;
        }
    }

    private static class getTimeSetsNotLiveAsyncTask extends AsyncTask<Void, Void, List<TimeSet>>{

        private final TimeSetDao mTimeSetDao;

        public getTimeSetsNotLiveAsyncTask(TimeSetDao mTimeSetDao) {
            this.mTimeSetDao = mTimeSetDao;
        }

        @Override
        protected List<TimeSet> doInBackground(Void... voids) {
            return mTimeSetDao.getAllNotLive();
        }
    }
}
