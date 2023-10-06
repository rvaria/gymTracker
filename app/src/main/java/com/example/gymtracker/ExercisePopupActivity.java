package com.example.gymtracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        parseExercises = new ParseExercises(this);

        try {
            json = parseExercises.convertFile();
            parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
