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

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_routines);

        exerciseDatabase = new ExerciseDatabase(this);
        routineItems = new ArrayList<>(exerciseDatabase.routinesList());

        System.out.println("ROUTINE ITEMS: " + routineItems);

        routineList = findViewById(R.id.routines);
        routineList.setClickable(true);
        routineList.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.listview_item, R.id.content, routineItems));


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
