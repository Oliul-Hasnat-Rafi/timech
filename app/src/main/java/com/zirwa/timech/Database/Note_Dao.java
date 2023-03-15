package com.zirwa.timech.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Note_Dao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM Note")
    List<Note> getNotes();

    @Query("SELECT * FROM Note  WHERE catagoris == :catagoris")
    List<Note> getAllNoteByCatagoris(String catagoris);


}
