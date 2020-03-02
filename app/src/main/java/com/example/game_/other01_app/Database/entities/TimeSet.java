package com.example.game_.other01_app.Database.entities;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "OTHER01timesets",
indices = {@Index(value = "exercisename", unique = true)})
public class TimeSet {
    @PrimaryKey(autoGenerate = true)
    private int tsid;
    @NonNull
    @ColumnInfo(name = "exercisename")
    private String exercisename;
    @ColumnInfo(name = "todaystime")
    private long recentTime;
    @ColumnInfo(name = "besttime")
    private long bestTime;
    @NonNull
    @ColumnInfo(name = "lastaccessed")
    private String lastaccessed;
    @ColumnInfo(name = "image")
    private String image;

    public TimeSet(@NotNull String exercisename, long recentTime,
                   long bestTime, @NotNull String lastaccessed, String image) {
        this.exercisename = exercisename;
        this.recentTime = recentTime;
        this.bestTime = bestTime;
        this.lastaccessed = lastaccessed;
        this.image = image;
    }

    public static TimeSet[] populateData(Exercise[] exercises) {
        ArrayList<TimeSet> timeSets = new ArrayList<>();
        for(Exercise exercise : exercises) {
            timeSets.add(
            new TimeSet(exercise.getName(), 0L,
                    0L,  "never", exercise.getImage())
            );
        }
        return timeSets.toArray(new TimeSet[0]);
    }

    @NotNull
    public String getExercisename() {
        return exercisename;
    }

    public void setExercisename(@NotNull String exercisename) {
        this.exercisename = exercisename;
    }

    @NotNull
    public String getLastaccessed() {
        return lastaccessed;
    }

    public void setLastaccessed(@NotNull String lastaccessed) {
        this.lastaccessed = lastaccessed;
    }

    public int getTsid() {
        return tsid;
    }

    public void setTsid(int tsid) {
        this.tsid = tsid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(long recentTime) {
        this.recentTime = recentTime;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void setBestTime(long bestTime) {
        this.bestTime = bestTime;
    }
}
