package com.example.gymtracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class ExerciseListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private ArrayList<String> exerciseItems;
    private TextView exerciseName;
    private CheckBox checkbox;


    public ExerciseListAdapter(Activity activity, ArrayList<String> exerciseItems) {
        super(activity ,R.layout.exercise_item, exerciseItems);
        this.activity = activity;
        this.exerciseItems = exerciseItems;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.exercise_item, parent, false);

        return convertView;
    }


}
