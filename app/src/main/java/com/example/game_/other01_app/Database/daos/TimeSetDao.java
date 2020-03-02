package com.example.game_.other01_app.Database.daos;

import android.icu.lang.UScript;

import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Database.entities.TimeSet;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TimeSetDao {

    @Query("SELECT * FROM OTHER01timesets")
    LiveData<List<TimeSet>> getAll();

    @Query("SELECT * FROM OTHER01timesets WHERE exercisename LIKE :exercisename" +
            " LIMIT 1")
    TimeSet findByExercise(String exercisename);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimeSet(TimeSet... timeSets);

    @Query("DELETE FROM OTHER01timesets")
    void deleteAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(TimeSet... timeSets);

    @Query("UPDATE " +
            "OTHER01timesets " +
            "SET todaystime = 0 " +
            "WHERE 1 = 1")
    void resetAllRecentTimes();

    @Query("UPDATE " +
            "OTHER01timesets " +
            "SET todaystime = :newTodaysTime, " +
            "lastaccessed = :newLastAccessed " +
            "WHERE exercisename = :exerciseName")
    void updateTimeSet(String exerciseName, long newTodaysTime,
                       String newLastAccessed);

    @Query("UPDATE " +
            "OTHER01timesets " +
            "SET besttime = :timeForUpdate " +
            "WHERE exercisename = :exercisename")
    void updateBestTime(String exercisename, long timeForUpdate);

    @Query("SELECT * FROM OTHER01timesets")
    List<TimeSet> getAllNotLive();
}
