package com.example.gymtracker;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<String> workoutItems;
    private TextView exercise;
    private TextView reps;
    private TextView weight;
    private EditText repEntry;
    private EditText weightEntry;
    private String item;
    private List<ExerciseData> dataList;

    public WorkoutListAdapter(Activity activity, List<String> workoutItems, List<ExerciseData> dataList) {
        super(activity, R.layout.exercise_item, workoutItems);
        this.activity = activity;
        this.workoutItems = workoutItems;
        this.dataList = dataList;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.workout_item, parent, false);

        }

        exercise = convertView.findViewById(R.id.exerciseName);
        reps = convertView.findViewById(R.id.reps);
        weight = convertView.findViewById(R.id.weight);
        repEntry = convertView.findViewById(R.id.repEntry);
        weightEntry = convertView.findViewById(R.id.weightEntry);

        item = workoutItems.get(position);
        exercise.setText(item);

        return convertView;
    }
}
