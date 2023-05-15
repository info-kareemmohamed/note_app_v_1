package com.example.noteapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.Entity.Note;
import com.example.noteapp.Repository.Note_repository;

import java.util.List;

public class Note_Viewmodel extends AndroidViewModel {
    private Note_repository repository;


    public Note_Viewmodel(@NonNull Application application) {
        super(application);
        repository = new Note_repository(application);
    }


    public void add_note(Note... note) {
        repository.add_note(note);
    }

    public void update_note(Note... note) {
        repository.update_note(note);
    }

    public void delete_note(Note... note) {
        repository.delete_note(note);


    }

    public LiveData<List<Note>> get_all_notes() {
        return repository.get_all_notes();

    }

    public void delete_note(long id) {
        repository.delete_note(id);


    }

    public void update_pin(long id, boolean pin) {
        repository.update_pin(id, pin);


    }

}
