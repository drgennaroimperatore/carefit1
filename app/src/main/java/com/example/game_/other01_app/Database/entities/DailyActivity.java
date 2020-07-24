package com.example.game_.other01_app.Database.entities;

public class DailyActivity
{
    public int id;
    public String name;
    public String instructions;
    public DailyActivityStatus status;
    public ExerciseTypes type;

    public DailyActivity()
    {
        status = DailyActivityStatus.NOT_ASSIGNED;
    }

}


