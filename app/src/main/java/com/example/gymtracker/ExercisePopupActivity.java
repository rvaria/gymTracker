package com.example.gymtracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;
    private ListView exerciseList;
    private ArrayAdapter<String> exerciseAdapter;
    private List<String> exercises;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        parseExercises = new ParseExercises(this);
        exerciseList = findViewById(R.id.exerciseList);

        try {
            json = parseExercises.convertFile();
            exercises = parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //exerciseAdapter = new ArrayAdapter(ExercisePopupActivity.this, android.R.layout.simple_list_item_1, exercises);
        //exerciseList.setAdapter(exerciseAdapter);

    }
}
