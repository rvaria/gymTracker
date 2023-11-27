package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExercisePopupActivity extends AppCompatActivity {

    private ParseExercises parseExercises;
    private String json;
    private ListView exerciseList;
    private List<String> exercises;
    private ExerciseListAdapter listAdapter;
    private SearchView searchExercises;
    private Button contButton;
    private Spinner muscleFilter;
    private ArrayList<String> chosenExercises;
    private String routineName;
    private ExerciseDatabase exerciseDatabase;
    private String[] muscleGroups;
    private ArrayList<String> selectedMuscle;
    private ArrayList<String> muscleFilterList;
    private long id;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_popup);

        Toolbar toolbar = findViewById(R.id.popupToolbar);
        setSupportActionBar(toolbar);

        parseExercises = new ParseExercises(this);
        exerciseList = findViewById(R.id.exerciseList);
        contButton = findViewById(R.id.contButton);
        muscleFilter = findViewById(R.id.muscleFilter);
        chosenExercises = new ArrayList<>();

        muscleGroups = new String[]{"All", "Abdominals", "Legs", "Biceps", "Back", "Chest", "Triceps", "Shoulders", "Forearms"};

        exerciseDatabase = new ExerciseDatabase(this);

        Intent getName = getIntent();
        routineName = getName.getExtras().getString("name");

        try {
            json = parseExercises.convertFile();
            exercises = parseExercises.readJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        ArrayAdapter<String> dropDown = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, muscleGroups);
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        muscleFilter.setAdapter(dropDown);
        muscleFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String muscle = muscleFilter.getSelectedItem().toString();
                selectedMuscle = new ArrayList<>();

                if (muscle.equals("Abdominals") || muscle.equals("Biceps") || muscle.equals("Chest")
                        || muscle.equals("Shoulders") || muscle.equals("Triceps") || muscle.equals("Forearms")) {
                    selectedMuscle.add(muscle);
                    muscleFilterList = new ArrayList<>(parseExercises.filterMuscle(json, selectedMuscle));
                    setList(muscleFilterList);
                } else if (muscle.equals("Legs")) {
                    selectedMuscle.addAll(Arrays.asList("Quadriceps", "Glutes", "Adductors", "Abductors", "Hamstrings", "Calves"));
                    muscleFilterList = new ArrayList<>(parseExercises.filterMuscle(json, selectedMuscle));
                    setList(muscleFilterList);
                } else if (muscle.equals("Back")) {
                    selectedMuscle.addAll(Arrays.asList("Traps", "Lats", "Middle Back", "Lower Back"));
                    muscleFilterList = new ArrayList<>(parseExercises.filterMuscle(json, selectedMuscle));
                    setList(muscleFilterList);
                } else {
                    setList(exercises);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenExercises.size() != 0) {

                    try {
                        id = exerciseDatabase.addRoutine(routineName);
                        exerciseDatabase.addExercises(chosenExercises, id);

                    } catch (Exception e) {
                        System.out.println("Not working");
                    }

                    Intent displayRoutine = new Intent(getApplicationContext(), DisplayWorkoutActivity.class);
                    displayRoutine.putExtra("name", routineName);
                    startActivity(displayRoutine);
                } else {
                    Toast.makeText(getApplicationContext(), "Please add an exercise!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setList(List<String> exerciseItems) {
        listAdapter = new ExerciseListAdapter(this, exerciseItems);
        exerciseList.setAdapter(listAdapter);
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
