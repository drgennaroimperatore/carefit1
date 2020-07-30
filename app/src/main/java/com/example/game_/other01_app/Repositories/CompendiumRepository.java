package com.example.game_.other01_app.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.CompendiumsDao;
import com.example.game_.other01_app.Database.entities.CompendiumActivities;

import java.util.List;

public class CompendiumRepository {
    private final CompendiumsDao mCompendiumsDao;
    private final LiveData<List<CompendiumActivities>> mCompendiums;

    public CompendiumRepository(Application app)
    {
        AppDatabase db = AppDatabase.getDatabase(app);
        mCompendiumsDao = db.compendiumsDao();
        mCompendiums = mCompendiumsDao.getAllCompendiums();
    }

    public LiveData<List<CompendiumActivities>> getmCompendiums() {return mCompendiums;}


}
