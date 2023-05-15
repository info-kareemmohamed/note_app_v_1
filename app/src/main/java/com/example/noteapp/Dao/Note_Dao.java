package com.example.noteapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.Entity.Note;

import java.util.List;

@Dao
public interface Note_Dao {
    @Insert
    public void add_note(Note... note);
    @Update
    public void update_note(Note... note);
    @Delete
    public void delete_note(Note ... note);
    @Query("select * from Note")
    public LiveData<List<Note>> get_all_notes();
    @Query("delete  from Note where Id=:id")
    public void delete_note(long id);
    @Query("update   Note set Pin=:pin where Id=:id")
    public void update_pin(long id,boolean pin);




}
