package com.example.noteapp.Ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;


import com.example.noteapp.R;
import com.example.noteapp.ViewModel.Note_model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  private  Intent intent;
  private  FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Note_model model= new ViewModelProvider(this).get(Note_model.class);
        inflate();
        FloatingActionButton_Set_Click();

    }


    private void inflate(){
        floatingActionButton=findViewById(R.id.Main_FloatingButton);
    }

    // To go to the note  screen
    private void FloatingActionButton_Set_Click(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this, Note.class);
                startActivity(intent);
            }
        });


    }


}