package com.example.game_.other01_app.Database.daos;

import com.example.game_.other01_app.Database.entities.Category;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoriesDao {
    @Query("SELECT * FROM OTHER01categories")
    LiveData<List<Category>> getAll();

    @Query("UPDATE OTHER01categories SET interested = :interested WHERE " +
    "category = :category")
    void updateInterest(String category, boolean interested);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(Category... categories);

    @Insert
    void insertAll(Category... categories);

    @Query("DELETE FROM OTHER01categories")
    void deleteAll();

    @Query("SELECT * FROM OTHER01categories")
    List<Category> getAllNotLive();
}
