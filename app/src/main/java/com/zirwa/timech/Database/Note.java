package com.zirwa.timech.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String title;
    @ColumnInfo
    String insertnote;
    @ColumnInfo
    String catagoris;
    @ColumnInfo
    String date;
    @ColumnInfo
    String time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsertnote() {
        return insertnote;
    }

    public void setInsertnote(String insertnote) {
        this.insertnote = insertnote;
    }

    public String getCatagoris() {
        return catagoris;
    }

    public void setCatagoris(String catagoris) {
        this.catagoris = catagoris;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", insertnote='" + insertnote + '\'' +
                ", catagoris='" + catagoris + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
