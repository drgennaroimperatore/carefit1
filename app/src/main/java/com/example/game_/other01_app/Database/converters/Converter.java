package com.example.game_.other01_app.Database.converters;

import androidx.room.TypeConverter;

import com.example.game_.other01_app.Database.entities.CompendiumActivitiesTypes;

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
}
