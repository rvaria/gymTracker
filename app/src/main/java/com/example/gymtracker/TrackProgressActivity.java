package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TrackProgressActivity extends AppCompatActivity {

    private ExerciseDatabase exerciseDatabase;
    private Spinner routines;
    private ListView exercises;
    private ArrayList<String> routineItems;
    private ArrayList<String> exerciseItems;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_progress);

        routines = findViewById(R.id.routineSpinner);
        exercises = findViewById(R.id.exercises);

        exerciseDatabase = new ExerciseDatabase(this);
        routineItems = new ArrayList<>(exerciseDatabase.routinesList());


        ArrayAdapter<String> dropDown = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, routineItems);
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routines.setAdapter(dropDown);

        routines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRoutine = routines.getSelectedItem().toString();
                exerciseItems = new ArrayList<>(exerciseDatabase.exercisesList(selectedRoutine));

                exercises.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.listview_item, R.id.content, exerciseItems));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exercises.setClickable(true);
        exercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = exercises.getItemAtPosition(position).toString();
                Intent exerciseProgress = new Intent(getApplicationContext(), ExerciseProgressActivity.class);
                exerciseProgress.putExtra("exercise", exercise);
                startActivity(exerciseProgress);
            }
        });
    }
}
