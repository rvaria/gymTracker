package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayRoutineActivity extends AppCompatActivity {

    private ArrayList<String> exercises;
    private TextView routine;
    private String routineName;
    private ExerciseDatabase exerciseDatabase;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_routine);

        Intent getRoutineName = getIntent();
        routine = findViewById(R.id.name);
        routineName = getRoutineName.getExtras().getString("name");
        routine.setText(routineName);
        exercises = getIntent().getStringArrayListExtra("chosenExercises");

        ExerciseDatabase exerciseDatabase = new ExerciseDatabase(this);

        try {
            Boolean checkInitial = exerciseDatabase.addInitialData(routineName, exercises);
            System.out.println("Initial data: " + checkInitial);
            Boolean checkRow = exerciseDatabase.addRow( "test", 1, 1.5);
            System.out.println("Initial row: " + checkRow);
            exerciseDatabase.results();
        } catch(Exception e) {
            System.out.println("Not working");
        }
    }

}
