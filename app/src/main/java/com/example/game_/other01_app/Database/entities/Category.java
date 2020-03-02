package com.example.game_.other01_app.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

@Entity(tableName = "OTHER01categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final int catid;
    @NonNull
    @ColumnInfo (name = "category")
    private String category;
    @NonNull
    @ColumnInfo (name = "interested")
    private final boolean interested;

    public Category(int catid, String category, boolean interested) {
        this.catid = catid;
        this.category = category;
        this.interested = interested;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isInterested() {
        return interested;
    }
    public int getCatid() {
        return catid;
    }

    public static Category[] populateData() {
        return new Category[] {
                new Category(1, "Arms", false),
                new Category(2, "Legs", false),
                new Category(3, "Back", false),
                new Category(4, "Sitting", false),
                new Category(5, "Strength", false),
                new Category(6, "Flexibility", false),
                new Category(7, "Balance", false),
                new Category(8, "Aerobic", false),
                new Category(9, "Stretch", false)
        };
    }
}
