package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EducationalList
{
    public EducationalList(int id, int stageNumber, String stageTitle )
    {
        this.id = id;
        this.stageNumber = stageNumber;
        this.stageTitle = stageTitle;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int stageNumber;
    public String stageTitle;

    public static EducationalList[] populateEducationalList()
    {
        return new EducationalList[]
                {
                        new EducationalList(1, 1,"Welcome and Introduction"),
                        new EducationalList(2, 2,"Physical Activity"),
                        new EducationalList(3, 3,"Relationships and Physical Activity"),
                        new EducationalList(4, 4,"Managing Time"),
                        new EducationalList(5, 5,"Goal Setting and Weekly Planner"),
                        new EducationalList(6, 6,"Physical Activity and Consequences"),
                        new EducationalList(7, 7,"The Mind and Body"),
                        new EducationalList(8, 8,"End Quiz")};
    }
}


