package com.example.gymtracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class DisplayWorkoutActivity extends AppCompatActivity {

    private TextView routine;
    private String routineName;
    private ExerciseDatabase exerciseDatabase;
    private WorkoutListAdapter workoutListAdapter;
    private Button openCalendar;
    private Button addData;
    private ListView workout;
    private TextView displayDate;
    private String date;
    private int cDay;
    private int cMonth;
    private int cYear;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_workout);

        Intent getRoutineName = getIntent();
        routine = findViewById(R.id.name);
        routineName = getRoutineName.getExtras().getString("name");
        routine.setText(routineName);

        Calendar calendar = Calendar.getInstance();
        cDay = calendar.get(Calendar.DAY_OF_MONTH);
        cMonth = calendar.get(Calendar.MONTH);
        cYear = calendar.get(Calendar.YEAR);

        workout = findViewById(R.id.workout);
        openCalendar = findViewById(R.id.calendar);
        displayDate = findViewById(R.id.date);
        addData = findViewById(R.id.addData);

        displayDate.setText(cDay + "/" + cMonth + "/" + cYear);

        exerciseDatabase = new ExerciseDatabase(this);

        ArrayList<String> data = new ArrayList<>(exerciseDatabase.exercisesList(routineName));

        workoutListAdapter = new WorkoutListAdapter(this, data);
        workout.setAdapter(workoutListAdapter);

        openCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public String datePicker() {

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        }, cYear, cMonth, cDay);

        datePicker.show();

        return date;
    }
}
