package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class WeeklyPlan {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public Date startDate;
    public Date endDate;
    public boolean isCurrent;
}
