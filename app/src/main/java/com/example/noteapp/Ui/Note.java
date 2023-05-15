package com.example.noteapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.ViewModel.NoteState_Viewmodel;
import com.example.noteapp.recyclerview.Adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note extends AppCompatActivity {
    Intent intent;
    NoteState_Viewmodel viewmodel;
    ImageView ImageButtonSave;
    EditText title, description;
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PIN = "pin";
    public static final String EXTRA_ID = "id";
    private boolean EditMode = false;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,d MMM yyyy HH:mm a");
    private com.example.noteapp.Entity.Note object = new com.example.noteapp.Entity.Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        inflate();
        Get_Data_Intent();
        Set_OnClick_ImageButtonSave();

    }


    private boolean TestText(String title, String note) {
        return title.equals("") || note.equals("");
    }

    private void Set_Note_In_Database() {
        Add_Data_In_Object();
        if (TestText(object.getTitle(), object.getDescription())) {
            Toast.makeText(this, R.string.message_test_text, Toast.LENGTH_LONG).show();
        } else {
            Add_Or_Update_Note(EditMode);
            finish();
        }
    }

    private void Add_Or_Update_Note(boolean editMode) {
        object.setDate(dateFormat.format(new Date()));
        if (EditMode)
            viewmodel.update_note(object);
        else
            viewmodel.add_note(object);
    }


    private void Add_Data_In_Object() {
        object.setTitle(title.getText().toString());
        object.setDescription(description.getText().toString());
        object.setDate(dateFormat.format(new Date()));
    }

    private void Get_Data_Intent() {
        intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            object.setPin(intent.getExtras().getBoolean(EXTRA_PIN));
            object.setId(Long.parseLong(intent.getExtras().getString(EXTRA_ID)));
            title.setText(intent.getExtras().getString(EXTRA_TITLE));
            description.setText(intent.getExtras().getString(EXTRA_DESCRIPTION));
            EditMode = true;
        }
    }


    private void Set_OnClick_ImageButtonSave() {
        ImageButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_Note_In_Database();
            }
        });

    }


    private void inflate() {
        viewmodel = new ViewModelProvider(this).get(NoteState_Viewmodel.class);
        ImageButtonSave = findViewById(R.id.Note_Image_Save_As);
        title = findViewById(R.id.Note_title);
        description = findViewById(R.id.Note_note);


    }
}