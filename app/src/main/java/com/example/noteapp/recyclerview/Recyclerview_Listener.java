package com.example.noteapp.recyclerview;

import androidx.cardview.widget.CardView;

import com.example.noteapp.Entity.Note;

public interface Recyclerview_Listener {
    public void OnClick(Note note);
    public void OnLong_Click(Note note, CardView cardView);



}
