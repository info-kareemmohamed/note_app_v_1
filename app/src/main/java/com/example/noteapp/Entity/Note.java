package com.example.noteapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
  private  long Id;

  private String Title;
  private String Description;
  private String Date;
  private boolean Pin;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Note(String title, String description, String date) {
        Title = title;
        Description = description;
        Date = date;
        Pin=false;
    }

    public Note() {
    }

    public Note( String title, String description, boolean pin,String date) {
Date=date;
        Title = title;
        Description = description;
        Pin = pin;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isPin() {
        return Pin;
    }

    public void setPin(boolean pin) {
        Pin = pin;
    }
}
