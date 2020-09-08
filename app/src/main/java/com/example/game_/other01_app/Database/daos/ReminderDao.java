package com.example.game_.other01_app.Database.daos;

import com.example.game_.other01_app.Database.entities.Reminder;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ReminderDao {

    /**
     * Also functions as an updater
     * @param reminder
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("SELECT * FROM RemindersV2")
    List<Reminder> getAllRemindersNotLive();

    @Query("SELECT * FROM RemindersV2 WHERE RemindersV2.strReminderID =:nid")
    Reminder findReminderByStrID(String nid);


}
