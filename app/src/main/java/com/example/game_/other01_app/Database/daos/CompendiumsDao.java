package com.example.game_.other01_app.Database.daos;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.game_.other01_app.Database.entities.CompendiumActivities;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CompendiumsDao {
@Insert
    void insertAll (List<CompendiumActivities> activities);
@Query("DELETE FROM COMPENDIUMACTIVITIES")
    void deleteAll();
@Query("SELECT * FROM CompendiumActivities")
    LiveData<List<CompendiumActivities>> getAllCompendiums();
@Query("SELECT * FROM compendiumactivities")
    List<CompendiumActivities> getCompendiumsSync();


}
