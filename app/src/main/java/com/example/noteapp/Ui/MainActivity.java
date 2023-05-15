package com.example.noteapp.Ui;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.Entity.Note;
import com.example.noteapp.R;
import com.example.noteapp.ViewModel.Note_Viewmodel;
import com.example.noteapp.recyclerview.Adapter;
import com.example.noteapp.recyclerview.Recyclerview_Listener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Note_Viewmodel model;
    private FloatingActionButton floatingActionButton;
    private List<Note> Main_list;
    private SearchView searchView;
    private PopupMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflate();
        FloatingActionButton_Set_Click();
        initialize_recycler();
        initialize_ViewModel_And_onChanged();
        SearchView_SetOnQueryTextListener();


    }


    private void initialize_recycler() {
        adapter = new Adapter(MainActivity.this, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }


    private void inflate() {
        floatingActionButton = findViewById(R.id.Main_FloatingButton);
        recyclerView = findViewById(R.id.Main_Recycler);
        searchView = findViewById(R.id.Main_Searchview);
    }

    private void initialize_ViewModel_And_onChanged() {
        model = new ViewModelProvider(this).get(Note_Viewmodel.class);
        model.get_all_notes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> list) {
                Main_list = list;
                adapter.setList(list);
            }
        });

    }

    // To go to the note screen
    private void FloatingActionButton_Set_Click() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, com.example.noteapp.Ui.Note.class);
                startActivity(intent);
            }
        });


    }

    public void SearchView_SetOnQueryTextListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filter(newText);
                return true;
            }
        });

    }

    //This function performs a search in the list according to the words written in the SearchView
    //
    public void filter(String text) {
        List<com.example.noteapp.Entity.Note> filterlist = new ArrayList<>();
        text.toLowerCase();
        for (Note note : Main_list) {
            if (note.getTitle().toLowerCase().contains(text) || note.getDescription().toLowerCase().contains(text)) {
                filterlist.add(note);
            }
        }
        adapter.setList(filterlist);
    }


    private void Set_Data_Intent(Note note) {
        intent = new Intent(MainActivity.this, com.example.noteapp.Ui.Note.class);
        intent.putExtra(com.example.noteapp.Ui.Note.EXTRA_TITLE, note.getTitle());
        intent.putExtra(com.example.noteapp.Ui.Note.EXTRA_DESCRIPTION, note.getDescription());
        intent.putExtra(com.example.noteapp.Ui.Note.EXTRA_PIN, note.isPin());
        intent.putExtra(com.example.noteapp.Ui.Note.EXTRA_ID, String.valueOf(note.getId()));
        startActivity(intent);
    }


    private void initialize_Popup_Menu(CardView cardView) {
        menu = new PopupMenu(this, cardView);
        menu.inflate(R.menu.popup);
    }

    private void Popup_Menu_onMenuItemClick(Note note) {
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Menu_pin:
                        onMenuItemClick_Menu_pin(note);
                        break;
                    default:
                        model.delete_note(note);

                }
                return true;
            }
        });
        menu.show();


    }

    private void onMenuItemClick_Menu_pin(Note note) {
        if (!note.isPin()) {
            model.update_pin(note.getId(), true);
            Toast.makeText(getApplicationContext(), R.string.message_pin, Toast.LENGTH_LONG).show();
        } else {
            model.update_pin(note.getId(), false);
            Toast.makeText(getApplicationContext(), R.string.message_unpin, Toast.LENGTH_LONG).show();
        }

    }


    private final Recyclerview_Listener listener = new Recyclerview_Listener() {
        @Override
        public void OnClick(Note note) {

            Set_Data_Intent(note);
        }

        @Override
        public void OnLong_Click(Note note, CardView cardView) {
            initialize_Popup_Menu(cardView);
            Popup_Menu_onMenuItemClick(note);
            ;
        }
    };
}