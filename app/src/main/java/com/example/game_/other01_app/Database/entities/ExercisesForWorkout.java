package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExercisesForWorkout {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int excerciseID;
    public int sets, reps;
    public long lengthOfTime;
    public float distance;
    public int workoutID;
}
