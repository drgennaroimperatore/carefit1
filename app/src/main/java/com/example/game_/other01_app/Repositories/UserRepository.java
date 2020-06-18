package com.example.game_.other01_app.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.UserDao;
import com.example.game_.other01_app.Database.entities.User;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private final UserDao mUserDao;
    private final LiveData<User> mUser;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUser();
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    public void createNewUser(User user) {
      //  User user = new User(id, yourName, theirName, checked);

        new createUserAsyncTask(mUserDao).execute(user);
    }

    public void increaseTodaysTotal(String exerciseName, Long exerciseTime) {
       new increaseTodaysTotalAsyncTask(mUserDao).execute(exerciseTime);
    }

    public User getUserNotLive() throws ExecutionException, InterruptedException {
        return new getUserNotLiveAsyncTask(mUserDao).execute().get();
    }

    public void increaseTotalAndIntensity(Long exerciseTime, String intensity) {
        Pair<Long, String> totintPair = new Pair<>(exerciseTime, intensity);
        new increaseTotalAndIntensityAsyncTask(mUserDao).execute(totintPair);
    }


    private static class createUserAsyncTask extends AsyncTask<User, Void, Void>{

        private final UserDao mUserDao;

        createUserAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mUserDao.deleteAll();
            mUserDao.insert(params[0]);
            return null;
        }
    }

    private class increaseTodaysTotalAsyncTask extends AsyncTask<Long, Void, Void>{

        private final UserDao mUserDao;

        public increaseTodaysTotalAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Long... longs) {
            mUserDao.increaseTodaysTotal(longs[0]);
            return null;
        }
    }

    private class getUserNotLiveAsyncTask extends AsyncTask<Void, Void, User> {
        private final UserDao mUserDao;
        public getUserNotLiveAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return mUserDao.getUserNotLive();
        }
    }

    private class increaseTotalAndIntensityAsyncTask extends AsyncTask<Pair<Long, String>, Void, Void> {

        private final UserDao mUserDao;

        public increaseTotalAndIntensityAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(Pair<Long, String>... pairs) {
            if(pairs[0].second.equals("mid")){
                mUserDao.increaseTotalAndMidIntensity(pairs[0].first, pairs[0].second);
            } else if(pairs[0].second.equals("high")) {
                mUserDao.increaseTotalAndHighntensity(pairs[0].first, pairs[0].second);
            } else {
                mUserDao.increaseTodaysTotal(pairs[0].first);
            }
            return null;
        }
    }
}
