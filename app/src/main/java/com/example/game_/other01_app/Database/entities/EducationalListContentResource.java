package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = EducationalListContent.class,
        parentColumns = "id", childColumns = "educationalListContentID")})
public class EducationalListContentResource
{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String resourceID;
    public int educationalListContentID;
}
