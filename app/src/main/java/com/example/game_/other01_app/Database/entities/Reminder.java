package com.example.game_.other01_app.Database.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "RemindersV2")
public class Reminder implements Parcelable  {

    public Reminder() {}

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private Date day;
    @NonNull
    private int hour;
    @NonNull
    private int minute;
    @NonNull
    private int notificationID;

    public void setId(int id) {
        this.id = id;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public void setDay(@NonNull Date day) {
        this.day = day;
    }

    @NonNull
    public Date getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getId() {
        return id;
    }

    public int getMinute() {
        return minute;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public Reminder(Date day, int minute, int hours, int nid) {
        this.day = day;
        this.minute = minute;
        this.hour = hours;
        this.notificationID = nid;
    }

    @Ignore
    public Reminder(Parcel in) {
        id = in.readInt();
        hour = in.readInt();
        minute = in.readInt();
        notificationID = Objects.requireNonNull(in.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //write the objects data to the passed-in parcel
        dest.writeInt(id);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(notificationID);
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
