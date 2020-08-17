package com.example.game_.other01_app.Database.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.game_.other01_app.Database.entities.EducationalList;
import com.example.game_.other01_app.Database.entities.EducationalListContent;

@Dao
public interface EducationalDao {

    @Insert
    long insertEducationalListElements(EducationalList... educationalList);

    @Insert
    long insertEducationalListContent(EducationalListContent educationalListContent);

    @Insert
    long insertEducationalListContentBulk(EducationalListContent... educationalListContents);
}
