package com.example.noteapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.Dao.Note_Dao;
import com.example.noteapp.Databace.databace;
import com.example.noteapp.Entity.Note;

import java.util.List;

public class Note_repository {
    private databace Instance;
    private Note_Dao dao;

    public Note_repository(Application application) {
        Instance = databace.getInstance(application);
        dao = Instance.dao();
    }


    public void add_note(Note... note) {
        Instance.executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.add_note(note);
            }
        });

    }

    public void update_note(Note... note) {
        Instance.executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.update_note(note);
            }
        });
    }

    public void delete_note(Note... note) {
        Instance.executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete_note(note);
            }
        });


    }

    public LiveData<List<Note>> get_all_notes() {
        return dao.get_all_notes();

    }

    public void delete_note(long id) {
        Instance.executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete_note(id);
            }
        });


    }

    public void update_pin(long id, boolean pin) {
        Instance.executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.update_pin(id, pin);
            }
        });


    }

}

