package com.example.game_.other01_app.Database.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "OTHER01Reminders")
public class Reminder implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private final int timeHrs;
    @NonNull
    private final int timeMins;
    @NonNull
    private final String repeating;

    public Reminder(int timeHrs, int timeMins, String repeating) {
        this.timeHrs = timeHrs;
        this.timeMins = timeMins;
        this.repeating = repeating;
    }

    @Ignore
    public Reminder(Parcel in) {
        id = in.readInt();
        timeHrs = in.readInt();
        timeMins = in.readInt();
        repeating = Objects.requireNonNull(in.readString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeHrs() {
        return timeHrs;
    }

    public int getTimeMins() {
        return timeMins;
    }

    @NonNull
    public String getRepeating() {
        return repeating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //write the objects data to the passed-in parcel
        dest.writeInt(id);
        dest.writeInt(timeHrs);
        dest.writeInt(timeMins);
        dest.writeString(repeating);
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

}
