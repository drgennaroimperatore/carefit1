package com.example.game_.other01_app.Repositories;

import android.app.Application;

import com.example.game_.other01_app.Database.AppDatabase;
import com.example.game_.other01_app.Database.daos.ExerciseDao;
import com.example.game_.other01_app.Database.entities.Exercise;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ExerciseRepository {

    private final ExerciseDao mExerciseDao;
    private final LiveData<List<Exercise>> mAllExercises;

    public ExerciseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mExerciseDao = db.exerciseDao();
        mAllExercises = mExerciseDao.getAllExercises();
    }

    public LiveData<List<Exercise>> getmAllExercises() {
        return mAllExercises;
    }

}
