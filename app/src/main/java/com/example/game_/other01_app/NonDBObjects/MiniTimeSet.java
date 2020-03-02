package com.example.game_.other01_app.NonDBObjects;

public class MiniTimeSet {

    private final String exerciseName;
    private final long newTodaysTime;
    private final String newLastAccessed;

    public MiniTimeSet(String exerciseName, Long newTodaysTime, String newLastAccessed){
        this.exerciseName = exerciseName;
        this.newTodaysTime = newTodaysTime;
        this.newLastAccessed = newLastAccessed;
    }

    public long getNewTodaysTime() {
        return newTodaysTime;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getNewLastAccessed() {
        return newLastAccessed;
    }
}
