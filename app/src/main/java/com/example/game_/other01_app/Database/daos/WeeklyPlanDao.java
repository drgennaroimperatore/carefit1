package com.example.game_.other01_app.Database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.game_.other01_app.Database.entities.WeeklyPlan;

@Dao
public interface WeeklyPlanDao {
    @Insert
    long addWeeklyPlan(WeeklyPlan plan);

    @Delete
    void deleteWeeklyPlan(WeeklyPlan plan);

    @Update
    void updateWeeklyPlan(WeeklyPlan plan);
}
