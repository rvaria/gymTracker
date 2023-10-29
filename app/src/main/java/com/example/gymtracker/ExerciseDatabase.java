package com.example.gymtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ExerciseDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase exerciseDatabase;
    private static final String table = "exercise_data";
    private static final String idCol = "id";
    private static final String routineCol = "routine_name";
    private static final String nameCol = "exercise_name";
    private static final String dateCol = "date";
    private static final String repsCol = "reps";
    private static final String weightCol = "weight";
    private static final int version = 1;

    public ExerciseDatabase(Context context) {
        super(context, table, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + table + "("
                + idCol + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + routineCol + "TEXT, "
                + nameCol + " TEXT, "
                + dateCol + " TEXT, "
                + repsCol + " INTEGER, "
                + weightCol + " REAL)";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addInitialData(String routine, ArrayList<String> exercises) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(String exercise : exercises) {
            values.put(routineCol, routine);
            values.put(nameCol, exercise);
        }

        long insert = db.insert(table, null, values);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addRow(String date, int reps, double weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dateCol, date);
        values.put(repsCol, reps);
        values.put(weightCol, weight);

        long insert = db.insert(table, null, values);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void results() {
        String queryString = "SELECT * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                String date = cursor.getString(2);
                int reps = cursor.getInt(3);
                double weight = cursor.getDouble(4);

                System.out.println("TEST OF ROW" + " date: " + date
                        + " reps: " + reps + " weight: " + weight);
            } while(cursor.moveToNext());
        }
        cursor.close();
    }
}
