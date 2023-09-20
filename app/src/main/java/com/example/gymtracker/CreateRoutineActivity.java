package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateRoutineActivity extends AppCompatActivity {

    private TextView name;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_routine);

        Intent routine = getIntent();
        name = findViewById(R.id.workoutName);
        name.setText(routine.getExtras().getString("name"));

    }
}