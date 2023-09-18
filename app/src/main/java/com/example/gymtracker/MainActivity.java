package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRoutine = findViewById(R.id.createRoutine);

        createRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routine = new Intent(getApplicationContext(), CreateRoutineActivity.class);
                startActivity(routine);
            }
        });
    }
}