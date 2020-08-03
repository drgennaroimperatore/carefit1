package com.example.game_.other01_app.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Objects;

@Entity
public class CompendiumActivities
{
    @PrimaryKey
    public int id;
    public String name;
    public  float mets;
    public CompendiumActivitiesTypes type;

    public CompendiumActivities (int id, String name, float mets, CompendiumActivitiesTypes type)
    {
        this.id = id;
        this.name = name;
        this.mets = mets;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompendiumActivities that = (CompendiumActivities) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static ArrayList<CompendiumActivities> populateTable()
    {
        int initialid =1;

        ArrayList<CompendiumActivities> compendiumActivities = new ArrayList<>();

        compendiumActivities.add(new CompendiumActivities(initialid++,"Bicycling",8.0f, CompendiumActivitiesTypes.BICYCLING ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Mild Stretching",2.5f, CompendiumActivitiesTypes.CONDITIONING ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Aerobic Step(6-8in)",8.5f, CompendiumActivitiesTypes.DANCING ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Aerobic Step(10-12in)",10.0f, CompendiumActivitiesTypes.DANCING ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Dancing",4.5f, CompendiumActivitiesTypes.DANCING ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Mopping",8.0f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Household Tasks (Light Effort)",2.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Household Tasks (Moderate Effort)",3.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Household Tasks (Vigorous Effort)",4.0f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Vacuuming",3.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Feeding Animals",2.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Watering Plants",2.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Building a Fire inside",2.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Carrying Small Children",2.5f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));
        compendiumActivities.add(new CompendiumActivities(initialid++,"Elder Care, disabled adults, active periods",4.0f, CompendiumActivitiesTypes.HOME_ACTIVITIES ));


        return compendiumActivities;

    }
}


