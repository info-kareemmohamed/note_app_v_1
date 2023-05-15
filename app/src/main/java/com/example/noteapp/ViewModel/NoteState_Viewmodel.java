package com.example.noteapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.noteapp.Entity.Note;
import com.example.noteapp.Repository.Note_repository;

public class NoteState_Viewmodel extends AndroidViewModel {
    Note_repository repository;

    public NoteState_Viewmodel(@NonNull Application application) {
        super(application);
        repository = new Note_repository(application);
    }

    public void add_note(Note... note) {
        repository.add_note(note);
    }

    public void update_note(Note... note) {
        repository.update_note(note);
    }
}
