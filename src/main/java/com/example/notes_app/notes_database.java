package com.example.notes_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;

import java.util.ArrayList;
import java.util.List;

public class notes_database extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Notes";
    public static final String DATABASE_TABLE = "notes_table";

    //column names
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";

    public notes_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INT PRIMARY KEY, " +
                KEY_TITLE + " TEXT, " +
                KEY_CONTENT + " TEXT" + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) return;

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }

    public long ADD_NOTE(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());

        long ID = db.insert(DATABASE_TABLE,null,c);

        return ID;
    }


    public Cursor GET_NOTES()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        /*if (cursor.moveToFirst())
        {

            do {
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));

                all_notes.add(note);

            }while(cursor.moveToNext());
        }*/
        return cursor;
    }

    public int UPDATE_NOTE(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT,note.getContent());

        return db.update(DATABASE_TABLE,c,KEY_ID+"=?",new String[]{String.valueOf(note.getID())});
    }

    public void DELETE_NOTE(long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+DATABASE_TABLE;
        //db.delete(DATABASE_TABLE,KEY_ID+" = ?",new String[]{String.valueOf(id)});
        db.execSQL(query);
        db.close();
    }

}
