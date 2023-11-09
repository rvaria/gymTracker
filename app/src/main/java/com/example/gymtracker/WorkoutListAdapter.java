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
import java.util.List;

public class WorkoutListAdapter extends ArrayAdapter<ExerciseData> {

    private Activity activity;
    private List<ExerciseData> workoutItems;
    private TextView exercise;
    private TextView reps;
    private TextView weight;

    public WorkoutListAdapter(Activity activity, List<ExerciseData> workoutItems) {
        super(activity, R.layout.exercise_item, workoutItems);
        this.activity = activity;
        this.workoutItems = workoutItems;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.workout_item, parent, false);

        exercise = convertView.findViewById(R.id.exerciseName);
        reps = convertView.findViewById(R.id.reps);
        weight = convertView.findViewById(R.id.weight);
        EditText repEntry = convertView.findViewById(R.id.repEntry);
        EditText weightEntry = convertView.findViewById(R.id.weightEntry);

        ExerciseData item = workoutItems.get(position);
        exercise.setText(item.getExerciseName());

        repEntry.setText(item.getReps());

        repEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setReps(s.toString());

            }
        });

        weightEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setReps(s.toString());

            }
        });


        return convertView;
    }
}


