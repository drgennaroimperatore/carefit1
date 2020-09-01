package com.example.game_.other01_app.Database.UsageDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.game_.other01_app.Sync.Syncable;

@Entity
public class ActivitiesStats implements Syncable {
    @Override
    public boolean sync() {
        return false;
    }
    @PrimaryKey
    public int id;
}
