package com.example.gymtracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;
    private ListView exerciseList;
    private List<String> exercises;
    private ExerciseListAdapter listAdapter;
    private SearchView searchExercises;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        Toolbar toolbar = findViewById(R.id.popupToolbar);
        setSupportActionBar(toolbar);
        parseExercises = new ParseExercises(this);
        exerciseList = findViewById(R.id.exerciseList);

        try {
            json = parseExercises.convertFile();
            exercises = parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listAdapter = new ExerciseListAdapter(this, exercises);
        exerciseList.setAdapter(listAdapter);
        searchExercises = findViewById((R.id.searchExercise));

        searchExercises.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                listAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
