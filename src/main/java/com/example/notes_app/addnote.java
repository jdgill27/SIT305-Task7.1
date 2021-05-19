package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addnote extends AppCompatActivity {

    EditText noteTitle, noteDetails;
    Button save, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        noteTitle = findViewById(R.id.notetitle);
        noteDetails = findViewById(R.id.notedetails);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);


    }

    public void add_note(View view) {
        Note note = new Note(noteTitle.getText().toString(), noteDetails.getText().toString());
        notes_database db = new notes_database(this);
        db.ADD_NOTE(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}