package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayWorkoutActivity extends AppCompatActivity {

    private TextView routine;
    private String routineName;
    private ExerciseDatabase exerciseDatabase;
    private WorkoutListAdapter workoutListAdapter;
    private ListView workout;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_workout);

        Intent getRoutineName = getIntent();
        routine = findViewById(R.id.name);
        routineName = getRoutineName.getExtras().getString("name");
        routine.setText(routineName);

        workout = findViewById(R.id.workout);

        exerciseDatabase = new ExerciseDatabase(this);

        ArrayList<String> data = new ArrayList<>(exerciseDatabase.exercisesList(routineName));

        workoutListAdapter = new WorkoutListAdapter(this, data);
        workout.setAdapter(workoutListAdapter);

    }

}
