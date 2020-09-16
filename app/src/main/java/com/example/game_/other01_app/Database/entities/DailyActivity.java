package com.example.game_.other01_app.Database.entities;

import androidx.room.ColumnInfo;
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
    public int mins,secs,millisecs;

    public DailyActivity(int dailyPlanId)
    {
        status = DailyActivityStatus.NOT_ASSIGNED;
        type = ExerciseTypes.UNASSIGNED;
        this.dailyPlanId = dailyPlanId;

    }

}


