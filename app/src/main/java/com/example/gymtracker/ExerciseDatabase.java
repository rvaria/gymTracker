package com.example.gymtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExerciseDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase exerciseDatabase;
    private static final String table = "exercise_data";
    private static final String id = "id";
    private static final String name = "name";
    private static final String date = "date";
    private static final String reps = "reps";
    private static final String weight = "weight";
    private static final int version = 1;

    public ExerciseDatabase(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + table
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + name + " TEXT, "
                + name + " TEXT, "
                + date + " TEXT, "
                + reps + " INTEGER, "
                + weight + " INTEGER)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
