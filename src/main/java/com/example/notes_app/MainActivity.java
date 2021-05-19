package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add;
    RecyclerView rv;
    List<Note> notes = new ArrayList<>();
    Adapter adapter;
    notes_database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.addbtn);
        db = new notes_database(this);
        getData();
        rv = findViewById(R.id.listOfNotes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,MainActivity.this,notes);
        rv.setAdapter(adapter);
    }

    private void getData() {
        Cursor cursor = db.GET_NOTES();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                notes.add(new Note(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    public void add_note(View view) {
        Intent intent = new Intent(MainActivity.this, addnote.class);
        startActivity(intent);
    }
}

/*android:layout_width="401dp"
        android:layout_height="135dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="2dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="2dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"*/