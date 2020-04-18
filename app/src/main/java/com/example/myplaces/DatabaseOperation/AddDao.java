package com.example.myplaces.DatabaseOperation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddDao {
    @Insert
    void insert(EntityTable addr);

    @Update
    void update(EntityTable addr);

    @Delete
    void delete(EntityTable addr);

    @Query("DELETE FROM entitytable")
    void deleteAll();

    @Query("SELECT * FROM entitytable ORDER BY priority DESC")
    LiveData <List<EntityTable>> getAll();

}
