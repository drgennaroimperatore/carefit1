package com.example.game_.other01_app.Repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.CategoriesDao;
import com.example.game_.other01_app.Database.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class CategoriesRepository {

    private final CategoriesDao mCategoriesDao;
    private final LiveData<List<Category>> mAllCategories;

    public CategoriesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mCategoriesDao = db.categoriesDao();
        mAllCategories = mCategoriesDao.getAll();
    }

    public LiveData<List<Category>> getmAllCategories() {
        return mAllCategories;
    }

    public void updatePreferences(ArrayList<Pair<String, Boolean>> preferencePairs) {
        new updateAllPreferencesAsyncTask(mCategoriesDao, preferencePairs).execute();
    }

    public List<Category> getmAllCategoriesNotNull() throws ExecutionException, InterruptedException {
        return new getAllNotNullAsyncTask(mCategoriesDao).execute().get();
    }

    private static class updateAllPreferencesAsyncTask extends AsyncTask<Void, Void, Void> {

        private final CategoriesDao mCategoriesDao;
        private final ArrayList<Pair<String, Boolean>> preferencePairs;

        updateAllPreferencesAsyncTask(CategoriesDao mCategoriesDao, ArrayList<Pair<String, Boolean>> preferencePairs) {
            this.mCategoriesDao = mCategoriesDao;
            this.preferencePairs = preferencePairs;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(Pair<String, Boolean> pair : preferencePairs){
                mCategoriesDao.updateInterest(pair.first, pair.second);
            }
            return null;
        }

    }

    private class getAllNotNullAsyncTask extends AsyncTask<Void, Void, List<Category>> {
        private final CategoriesDao mCategoriesDao;
        public getAllNotNullAsyncTask(CategoriesDao mCategoriesDao) {
            this.mCategoriesDao = mCategoriesDao;
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            return mCategoriesDao.getAllNotLive();
        }
    }
}
