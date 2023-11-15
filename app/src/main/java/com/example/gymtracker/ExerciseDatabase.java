package com.example.gymtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDatabase extends SQLiteOpenHelper {

    private static final String database = "exercise_database";
    private static final String routineTable = "routine";
    private static final String exerciseTable = "exercise";
    private static final String dataTable = "user_data";
    private static final String routineID = "routine_id";
    private static final String routineIDKey = "routine_id_key";
    private static final String exerciseID = "exercise_id";
    private static final String exerciseIDKey = "exercise_id_key";
    private static final String dataID = "data_id";
    private static final String routineCol = "routine_name";
    private static final String nameCol = "exercise_name";
    private static final String dateCol = "date";
    private static final String repsCol = "reps";
    private static final String weightCol = "weight";
    private static final int version = 1;

    public ExerciseDatabase(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String routine = "CREATE TABLE " + routineTable + "("
                + routineID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + routineCol + " TEXT)";

        String exercise = "CREATE TABLE " + exerciseTable + "("
                + exerciseID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + nameCol + " TEXT, "
                + routineIDKey + " INTEGER, FOREIGN KEY (" + routineIDKey + ") REFERENCES "
                + routineTable + "(" + routineID + "))";

        String data = "CREATE TABLE " + dataTable + "("
                + dataID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + dateCol + " TEXT, "
                + repsCol + " INTEGER, "
                + weightCol + " REAL, "
                + exerciseIDKey + " INTEGER, FOREIGN KEY (" + exerciseIDKey + ") REFERENCES "
                + exerciseTable + "(" + exerciseID + "))";

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL(routine);
        db.execSQL(exercise);
        db.execSQL(data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addRoutine(String routine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(routineCol, routine);

        long id = db.insert(routineTable, null, values);
        return id;
    }

    public void addExercises(ArrayList<String> exercises, long keyID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(String exercise : exercises) {
            values.put(nameCol, exercise);
            values.put(routineIDKey, keyID);
            db.insert(exerciseTable, null, values);

        }

    }

    public boolean addData(String date, int reps, double weight, long keyID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dateCol, date);
        values.put(repsCol, reps);
        values.put(weightCol, weight);
        values.put(exerciseIDKey, keyID);

        long insert = db.insert(dataTable, null, values);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> routinesList() {

        String query = "SELECT " + routineCol + " FROM " + routineTable;

        ArrayList<String> routines = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                routines.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }

        return routines;
    }

    public List<String> exercisesList(String routine) {

        String queryString = "SELECT " + nameCol + " FROM " + exerciseTable
                + " INNER JOIN " + routineTable + " ON "
                + routineTable + "." + routineID + "=" + exerciseTable + "." + routineIDKey
                + " WHERE " + routineTable + "." + routineCol + " = '" + routine + "'";


        ArrayList<String> exercises = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                exercises.add(cursor.getString(0));

            } while(cursor.moveToNext());
        }

        cursor.close();
        return exercises;

    }

}
