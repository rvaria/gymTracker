package com.example.gymtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button createRoutine;
    private Button viewRoutine;
    private Button trackProgress;
    private String dialogPrompt;
    public EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        createRoutine = findViewById(R.id.createRoutine);
        viewRoutine = findViewById(R.id.viewRoutine);
        trackProgress = findViewById(R.id.trackProgress);
        dialogPrompt = "Enter your workout routine name";


        createRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputName = new EditText(MainActivity.this);
                AlertDialog.Builder createBox = new AlertDialog.Builder(MainActivity.this);
                createBox.setTitle(dialogPrompt);
                createBox.setView(inputName);

                createBox.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent create = new Intent(getApplicationContext(), CreateRoutineActivity.class);
                        create.putExtra("name", inputName.getText().toString());
                        startActivity(create);

                    }
                });

                createBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                createBox.show();

            }
        });

        trackProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent progress = new Intent(getApplicationContext(), TrackProgressActivity.class);
                startActivity(progress);

            }
        });

        viewRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(getApplicationContext(), DisplayRoutinesActivity.class);
                startActivity(view);

            }
        });
    }
}