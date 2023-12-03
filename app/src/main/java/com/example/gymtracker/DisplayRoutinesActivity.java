package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DisplayRoutinesActivity extends AppCompatActivity {

    private ListView routineList;
    private List<String> routineItems;
    private ExerciseDatabase exerciseDatabase;
    private RoutineListAdapter routineListAdapter;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_routines);

        exerciseDatabase = new ExerciseDatabase(this);
        routineItems = new ArrayList<>(exerciseDatabase.routinesList());

        routineList = findViewById(R.id.routines);
        routineList.setClickable(true);
        routineListAdapter = new RoutineListAdapter(this, routineItems);
        routineList.setAdapter(routineListAdapter);


        routineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent workout = new Intent(getApplicationContext(), DisplayWorkoutActivity.class);
                String name = routineList.getItemAtPosition(position).toString();
                workout.putExtra("name", name);
                startActivity(workout);

            }
        });
    }
}
