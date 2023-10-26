package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayRoutineActivity extends AppCompatActivity {

    private ArrayList<String> exercises;
    private TextView routineName;
    private ExerciseDatabase exerciseDatabase;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_routine);

        Intent getRoutineName = getIntent();
        routineName = findViewById(R.id.name);
        routineName.setText(getRoutineName.getExtras().getString("name"));
        exercises = getIntent().getStringArrayListExtra("chosenExercises");

        ExerciseDatabase exerciseDatabase = new ExerciseDatabase(this);

        try {
            exerciseDatabase.addRow( "Test", "test", 1, 1.5);
            exerciseDatabase.results();
        } catch(Exception e) {
            System.out.println("Not working");
        }
    }

}
