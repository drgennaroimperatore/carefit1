package com.example.game_.other01_app.Database.daos;

import com.example.game_.other01_app.Database.entities.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM OTHER01user LIMIT 1")
    LiveData<User> getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM OTHER01user")
    void deleteAll();

    @Query("UPDATE " +
            "OTHER01user " +
            "SET recent_total_exercise_time = recent_total_exercise_time + :aLong " +
            "WHERE 1 = 1")
    void increaseTodaysTotal(Long aLong);

    @Query("SELECT * FROM OTHER01user LIMIT 1")
    User getUserNotLive();

    @Query("UPDATE " +
            "OTHER01user " +
            "SET recent_total_exercise_time = recent_total_exercise_time + :aLong," +
            "recent_highest_intensity = " +
            "CASE WHEN recent_highest_intensity = 'low' THEN :intensity " +
            "ELSE recent_highest_intensity END " +
            "WHERE 1 = 1")
    void increaseTotalAndMidIntensity(Long aLong, String intensity);

    @Query("UPDATE " +
            "OTHER01user " +
            "SET recent_total_exercise_time = recent_total_exercise_time + :aLong," +
            "recent_highest_intensity = :intensity " +
            "WHERE 1 = 1")
    void increaseTotalAndHighntensity(Long aLong, String intensity);
}
