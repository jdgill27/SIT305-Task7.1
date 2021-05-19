package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class my_note extends AppCompatActivity {

    public EditText note_title, note_detail;
    notes_database db;
    Note note = new Note();

    public long id;
    public String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);

        Intent intent = getIntent();
        id = intent.getLongExtra("iid",0);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

        db = new notes_database(this);

        note.setID(id);
        note.setTitle(title);
        note.setContent(content);

        note_title = findViewById(R.id.notetitle);
        note_detail = findViewById(R.id.notedetails);

        note_title.setText(note.getTitle());
        note_detail.setText(note.getContent());
    }

    public void delete_note(View view) {
        Toast.makeText(this, "delete pressed", Toast.LENGTH_SHORT).show();
        db.DELETE_NOTE(note.getID());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void update_note(View view) {
        note.setTitle(note_title.getText().toString());
        note.setContent(note_detail.getText().toString());
        int res = db.UPDATE_NOTE(note);

        if (res == -1)
        {
            Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "update pressed", Toast.LENGTH_SHORT).show();

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}