package com.zirwa.timech.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract Note_Dao getNoteDao();


    static NoteDatabase note_database = null;


    public static NoteDatabase getInstance(Context context) {


        if (note_database == null) {
            note_database = Room.databaseBuilder(
                            context,
                            NoteDatabase.class,
                            "Note_db")
                    .allowMainThreadQueries()
                    .build();
        }


        return note_database;

    }

}
