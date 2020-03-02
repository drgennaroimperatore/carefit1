package com.example.game_.other01_app.Database.daos;

import com.example.game_.other01_app.Database.entities.Exercise;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//DAO means database access object. defines the methods that access the database
//uses annotation to bind SQL to each method.
@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM OTHER01exercises")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM OTHER01exercises WHERE name LIKE :name" +
            " LIMIT 1")
    Exercise findByName (String name);

    @Insert
    void insertAll(Exercise... exercises);

    @Query("DELETE FROM OTHER01EXERCISES")
    void deleteAll();

}
