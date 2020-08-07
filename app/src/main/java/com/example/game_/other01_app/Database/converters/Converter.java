package com.example.game_.other01_app.Database.converters;

import androidx.room.TypeConverter;

import com.example.game_.other01_app.Database.entities.CompendiumActivitiesTypes;
import com.example.game_.other01_app.Database.entities.DailyActivityStatus;
import com.example.game_.other01_app.Database.entities.ExerciseTypes;

import java.util.Date;

public class Converter
{
    @TypeConverter
    public String fromCompendiumType (CompendiumActivitiesTypes type)
    {
        return type.toString();
    }

    @TypeConverter
    public CompendiumActivitiesTypes toCompendiumType(String type)
    {
        return CompendiumActivitiesTypes.valueOf(type);
    }

    @TypeConverter
    public String fromDailyActivityStatus (DailyActivityStatus type)
    {
        return type.toString();
    }

    @TypeConverter
    public DailyActivityStatus toDailyActivityStatus(String type)
    {
        return DailyActivityStatus.valueOf(type);
    }

    @TypeConverter
    public String fromExerciseType (ExerciseTypes type)
    {
        return type.toString();
    }

    @TypeConverter
    public ExerciseTypes toExerciseType(String type)
    {
        return ExerciseTypes.valueOf(type);
    }

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }


}
