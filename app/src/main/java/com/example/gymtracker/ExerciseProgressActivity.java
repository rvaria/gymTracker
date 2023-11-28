package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ExerciseProgressActivity extends AppCompatActivity {

    private ExerciseDatabase exerciseDatabase;
    private List<WorkoutEntry> exerciseData;
    private String routine;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_progress);

        exerciseDatabase = new ExerciseDatabase(this);

        Intent getRoutine = getIntent();
        routine = getRoutine.getExtras().getString("exercise");

        exerciseData = new ArrayList<>(exerciseDatabase.getData(routine));


    }
}
