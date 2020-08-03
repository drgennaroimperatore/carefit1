package com.example.game_.other01_app.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.Date;
import java.util.List;

@Dao
public interface WeeklyPlanDao {
    @Insert
    long addWeeklyPlan(WeeklyPlan plan);

    @Delete
    void deleteWeeklyPlan(WeeklyPlan plan);

    @Update
    void updateWeeklyPlan(WeeklyPlan plan);

    @Query("SELECT * FROM WeeklyPlan WHERE WeeklyPlan.startDate=:date")
    LiveData<List<WeeklyPlan>> getWeeklyPlanbyStartDate(Date date);

    @Query("SELECT COUNT (*) FROM WEEKLYPLAN" )
    LiveData<Integer> getWeeklyPlanCount();

    @Query("SELECT * FROM weeklyplan")
    LiveData<List<WeeklyPlan>> getAllWeeklyPlans();

    @Query("SELECT * FROM weeklyplan")
    List<WeeklyPlan> getWeeklyPlans();


}
