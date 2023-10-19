package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;
    private ListView exerciseList;
    private List<String> exercises;
    private ExerciseListAdapter listAdapter;
    private SearchView searchExercises;
    private Button contButton;
    private ArrayList<String> chosenExercises;
    private String routineName;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        Toolbar toolbar = findViewById(R.id.popupToolbar);
        setSupportActionBar(toolbar);

        parseExercises = new ParseExercises(this);
        exerciseList = findViewById(R.id.exerciseList);
        contButton = findViewById(R.id.contButton);
        chosenExercises = new ArrayList<>();

        Intent getName = getIntent();
        routineName = getName.getExtras().getString("name");

        try {
            json = parseExercises.convertFile();
            exercises = parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        listAdapter = new ExerciseListAdapter(this, exercises);
        exerciseList.setAdapter(listAdapter);
        searchExercises = findViewById((R.id.searchExercise));

        searchExercises.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                listAdapter.notifyDataSetChanged();
                return false;
            }
        });

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chosenExercises.size() != 0) {
                    Intent displayRoutine = new Intent(getApplicationContext(), DisplayRoutineActivity.class);
                    displayRoutine.putStringArrayListExtra("chosenExercises", chosenExercises);
                    displayRoutine.putExtra("name", routineName);
                    startActivity(displayRoutine);
                } else {
                    Toast.makeText(getApplicationContext(), "Please add an exercise!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setCount(int count) {
        contButton.setText("Add (" + Integer.toString(count) + ")");
    }

    public void setItems(String item) {
        chosenExercises.add(item);
    }

    public void removeItems(String item) {
        chosenExercises.remove(item);
    }

}
