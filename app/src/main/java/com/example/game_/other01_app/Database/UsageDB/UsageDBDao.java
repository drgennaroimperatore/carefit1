package com.example.game_.other01_app.Database.UsageDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsageDBDao {
    @Insert
    long InsertActivityStats(ActivitiesStats activitiesStats);

    @Update
    void UpdateActivityStats (ActivitiesStats activitiesStats);

    @Delete
    void DeleteActivityStats(ActivitiesStats activitiesStats);

    @Query("SELECT * FROM ActivitiesStats LIMIT 1")
    ActivitiesStats getActivitiesStats();

}
