package com.example.gymtracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;
    private List<String> exerciseList;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        parseExercises = new ParseExercises(this);

        try {
            json = parseExercises.convertFile();
            exerciseList = parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
