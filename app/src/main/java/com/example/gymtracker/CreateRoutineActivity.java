package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateRoutineActivity extends AppCompatActivity {

    private TextView name;
    private Button addExercise;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_routine);

        Toolbar toolbar = findViewById(R.id.routineToolbar);
        setSupportActionBar(toolbar);
        addExercise = findViewById(R.id.addExercise);

        Intent routine = getIntent();
        name = findViewById(R.id.workoutName);
        name.setText(routine.getExtras().getString("name"));

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popup = new Intent(getApplicationContext(), ExercisePopupActivity.class);
                startActivity(popup);
            }
        });
    }

}