package com.example.game_.other01_app.Database.UsageDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.game_.other01_app.Sync.SyncManager;
import com.example.game_.other01_app.Sync.Syncable;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class ActivitiesStats extends Syncable {

    @PrimaryKey
    public int id;
    public int cardioActivitiesStarted;
    public int muscleBalanceActivitiesStarted;
    public int compendiumActivitiesStarted;
    public int cardioActivitiesCompleted;
    public int muscleBalanceActivitiesCompleted;
    public int compendiumActivitiesCompleted;


    @Override
    public String sync() {
        Map<String, Object> activitiesStats = new LinkedHashMap<>();
        activitiesStats.put("cardioActivtiesStarted",cardioActivitiesStarted);
        activitiesStats.put("compendiumActivitiesStarted",compendiumActivitiesStarted);
        activitiesStats.put(" cardioActivitiesCompleted",cardioActivitiesCompleted);
        activitiesStats.put("muscleBalanceActivitiesCompleted",muscleBalanceActivitiesCompleted);
        activitiesStats.put("compendiumActivitiesCompleted",compendiumActivitiesCompleted);
        activitiesStats.put("muscleBalanceActivitiesStarted",muscleBalanceActivitiesStarted);

        return SyncManager.getInstance().sendPost("syncActivitiesStats",activitiesStats);
    }

}
