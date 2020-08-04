package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = DailyPlan.class,
        parentColumns = "id",
        childColumns = "dailyPlanId", onDelete = ForeignKey.CASCADE)})
public class DailyActivity
{
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String name;
    public String instructions;
    public DailyActivityStatus status;
    public ExerciseTypes type;
    public int dailyPlanId;

    public DailyActivity()
    {
        status = DailyActivityStatus.NOT_ASSIGNED;
    }

}


