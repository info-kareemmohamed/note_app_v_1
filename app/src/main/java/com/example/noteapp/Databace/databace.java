package com.example.noteapp.Databace;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteapp.Dao.Note_Dao;
import com.example.noteapp.Entity.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1)
public abstract class databace extends RoomDatabase {
    public abstract Note_Dao dao();
    private static volatile databace Instance;
    public ExecutorService executorService= Executors.newFixedThreadPool(4);


    public static synchronized databace getInstance(Context context){
        if(Instance==null){
            Instance= Room.databaseBuilder(context.getApplicationContext(),databace.class,"Notebook").fallbackToDestructiveMigration().build();
        }

        return Instance;
    }




}
