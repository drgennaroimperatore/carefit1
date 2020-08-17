package com.example.game_.other01_app.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.game_.other01_app.Database.entities.EducationalList;
import com.example.game_.other01_app.Database.entities.EducationalListContent;

import java.util.List;

@Dao
public interface EducationalDao {

    @Insert
    void insertEducationalListElements(EducationalList... educationalList);

    @Insert
    long insertEducationalListContent(EducationalListContent educationalListContent);

    @Insert
    void insertEducationalListContentBulk(EducationalListContent... educationalListContents);

    @Query("SELECT * FROM EducationalList")
    List<EducationalList> getEducationalList();
}
