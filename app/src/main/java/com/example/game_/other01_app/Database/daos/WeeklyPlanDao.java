package com.example.game_.other01_app.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.game_.other01_app.Database.entities.DailyActivity;
import com.example.game_.other01_app.Database.entities.DailyPlan;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;
import com.example.game_.other01_app.Database.entities.UnassignedDailyActivities;
import com.example.game_.other01_app.Database.entities.WeeklyPlan;

import java.util.Date;
import java.util.List;

import io.reactivex.internal.operators.flowable.FlowableWithLatestFromMany;

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

    @Query("SELECT * FROM DailyPlan WHERE DailyPlan.weeklyPlanID =:weeklyPlanID")
    List<DailyPlan> getDailyPlansByWeeklyPlanID(int weeklyPlanID);


    @Insert
    long addDailyPlan(DailyPlan dailyPlan);

    @Delete
    void deleteDailyPlan(DailyPlan dailyPlan);

    @Query("SELECT * FROM DailyActivity WHERE DailyActivity.dailyPlanId=:dailyPlanID")
    List<DailyActivity> getDailyActivitiesByDailyPlanID(int dailyPlanID);

    @Query("SELECT * FROM dailyactivity WHERE DailyActivity.id=:id LIMIT 1")
    DailyActivity getDailyActivityByID(int id);

    @Query("SELECT * FROM DailyActivity WHERE DailyActivity.dailyPlanId=:dailyPlanID")
    LiveData<List<DailyActivity>> getDailyActivitiesByDailyPlanIDLiveData(int dailyPlanID);

    @Query("SELECT DailyActivity.id, DailyActivity.dailyPlanId, DailyPlan.dayOfWeek, DailyActivity.status " +
            "FROM DailyActivity,DailyPlan WHERE DailyActivity.dailyPlanId = DailyPlan.id " +
            "AND DailyActivity.status = \"NOT_ASSIGNED\"/* AND DailyPlan.dayOfWeek >=DATE('now') */")
    List<UnassignedDailyActivities> getAllUnassignedActivities();
    @Insert
    long addDailyActivity(DailyActivity dailyActivity);

    @Delete
    void deleteDailyActivity(DailyActivity dailyActivity);

    @Update
    void updateDailyActivity(DailyActivity dailyActivity);

    @Query("SELECT COUNT(*) FROM DailyPlan, DailyActivity WHERE DailyPlan.id = DailyActivity.dailyPlanId " +
            "AND DailyPlan.weeklyPlanID =:wpid and DailyActivity.type =:activityType and DailyActivity.status=\"ASSIGNED\"")
            int getPlannedActivityCountByTypeAndWeeklyPlanID (int wpid, ExerciseTypes activityType);

    @Query("SELECT COUNT(*) FROM DailyPlan, DailyActivity WHERE DailyPlan.id = DailyActivity.dailyPlanId " +
            "AND DailyPlan.weeklyPlanID =:wpid and DailyActivity.type =:activityType and DailyActivity.status=\"COMPLETED\"")
    int getCompletedActivityCountByTypeAndWeeklyPlanID (int wpid, ExerciseTypes activityType);


}
