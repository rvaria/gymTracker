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
    private ArrayList<String> exercisesList;
    private ExerciseDatabase exerciseDatabase;
    private long id;

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
            id = exerciseDatabase.addRoutine(routineName);
            exerciseDatabase.addExercises(exercises, id);


            ArrayList<String> data = new ArrayList<>(exerciseDatabase.results());
            System.out.println("THE DATA IS " + data);

        } catch(Exception e) {
            System.out.println("Not working");
        }
    }

}
