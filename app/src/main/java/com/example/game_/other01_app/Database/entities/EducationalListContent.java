package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = EducationalList.class,
        parentColumns = "id", childColumns = "educationalListId", onDelete = ForeignKey.CASCADE)})
public class EducationalListContent {

    public EducationalListContent(int id, int educationalListId, int pageNumber, String content)
    {
        this.id = id;
        this.educationalListId = educationalListId;
        this.pageNumber = pageNumber;
        this.content = content;
    }
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int educationalListId;
    public int pageNumber;
    public String content;


}


