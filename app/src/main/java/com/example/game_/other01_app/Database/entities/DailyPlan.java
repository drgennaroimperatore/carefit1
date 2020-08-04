package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = WeeklyPlan.class, parentColumns = "id",
        childColumns = "weeklyPlanID", onDelete = ForeignKey.CASCADE)})
public class DailyPlan
{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public Date dayOfWeek;
    public int weeklyPlanID;
}
