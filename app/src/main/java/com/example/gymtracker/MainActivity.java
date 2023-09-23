package com.example.gymtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button createRoutine;
    private String dialogPrompt;
    public EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRoutine = findViewById(R.id.createRoutine);
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
                            Intent routine = new Intent(getApplicationContext(), CreateRoutineActivity.class);
                            routine.putExtra("name", inputName.getText().toString());
                            startActivity(routine);

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
    }
}