package com.example.game_.other01_app.Database.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "OTHER01user")
public class User implements Parcelable {
    @PrimaryKey
    private int id;
    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;
    @NonNull
    @ColumnInfo (name = "care_name")
    private String careName;
    @ColumnInfo (name = "exercise_with")
    private boolean exerciseWith;
    @ColumnInfo (name = "last_log_in")
    private Long last_log_in;
    @ColumnInfo(name = "recent_total_exercise_time")
    private Long recentTotalExerciseTime;
    @ColumnInfo(name = "best_total_exercise_time")
    private Long bestTotalExerciseTime;
    @ColumnInfo(name = "best_highest_intensity")
    private String bestHighestIntensity;
    @ColumnInfo(name = "recent_highest_intensity")
    private String recentHighestIntensity;
    @ColumnInfo(name = "tried_exercises")
    private String triedExercises;
    @ColumnInfo(name = "days_in_a_row")
    private int streak;

    public User(int id, String userName, String careName, boolean exerciseWith,
                long last_log_in, long recentTotalExerciseTime,
                long bestTotalExerciseTime, String bestHighestIntensity,
                String recentHighestIntensity, String triedExercises,
                int streak){
        this.id = id;
        this.userName = userName;
        this.careName = careName;
        this.exerciseWith = exerciseWith;
        this.last_log_in = last_log_in;
        this.recentTotalExerciseTime = recentTotalExerciseTime;
        this.bestTotalExerciseTime = bestTotalExerciseTime;
        this.bestHighestIntensity = bestHighestIntensity;
        this.recentHighestIntensity = recentHighestIntensity;
        this.triedExercises = triedExercises;
        this.streak = streak;

    }

    @Ignore
    public User (Parcel in) {
        this.id = in.readInt();
        this.userName = in.readString();
        this.careName = in.readString();
        this.exerciseWith = in.readByte() != 0;
        this.last_log_in = in.readLong();
        this.recentTotalExerciseTime = in.readLong();
        this.bestTotalExerciseTime = in.readLong();
        this.bestHighestIntensity = in.readString();
        this.recentHighestIntensity = in.readString();
        this.triedExercises = in.readString();
        this.streak = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //write the objects data to the passed-in parcel
        dest.writeInt(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.careName);
        dest.writeByte((byte) (this.exerciseWith ? 1 : 0));
        dest.writeLong(this.last_log_in);
        dest.writeLong(this.recentTotalExerciseTime);
        dest.writeLong(this.bestTotalExerciseTime);
        dest.writeString(this.bestHighestIntensity);
        dest.writeString(this.recentHighestIntensity);
        dest.writeString(this.triedExercises);
        dest.writeInt(this.streak);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCareName() {
        return careName;
    }

    public void setCareName(String careName) {
        this.careName = careName;
    }

    public boolean isExerciseWith() {
        return exerciseWith;
    }

    public void setExerciseWith(boolean exerciseWith) {
        this.exerciseWith = exerciseWith;
    }

    public Date getLast_log_in_as_Date() {
        return new Date(last_log_in);
    }

    public void setLast_log_in(Long last_log_in) {
        this.last_log_in = last_log_in;
    }

    public void setLast_log_in(Date log_in_time) {
        this.last_log_in = dateToTimestamp(log_in_time);
    }

    @TypeConverter
    private Long dateToTimestamp(Date last_log_in) {
        return last_log_in.getTime();
    }

    public Long getRecentTotalExerciseTime() {
        return recentTotalExerciseTime;
    }

    public void setRecentTotalExerciseTime(Long recentTotalExerciseTime) {
        this.recentTotalExerciseTime = recentTotalExerciseTime;
    }

    public Long getBestTotalExerciseTime() {
        return bestTotalExerciseTime;
    }

    public void setBestTotalExerciseTime(Long bestTotalExerciseTime) {
        this.bestTotalExerciseTime = bestTotalExerciseTime;
    }

    public String getBestHighestIntensity() {
        return bestHighestIntensity;
    }

    public void setBestHighestIntensity(String lastHighestIntensity) {
        this.bestHighestIntensity = lastHighestIntensity;
    }

    public String getRecentHighestIntensity() {
        return recentHighestIntensity;
    }

    public void setRecentHighestIntensity(String recentHighestIntensity) {
        this.recentHighestIntensity = recentHighestIntensity;
    }

    public String getTriedExercises() {
        return triedExercises;
    }

    public void setTriedExercises(String triedExercises) {
        this.triedExercises = triedExercises;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public Long getLast_log_in() {
        return last_log_in;
    }


}
