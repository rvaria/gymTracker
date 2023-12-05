package com.example.gymtracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TableListAdapter extends ArrayAdapter<WorkoutEntry> {

    private Activity activity;
    private List<WorkoutEntry> tableItems;
    private TextView tableDate;
    private TextView tableReps;
    private TextView tableWeight;

    public TableListAdapter(Activity activity, List<WorkoutEntry> tableItems) {
        super(activity, R.layout.table_item, tableItems);
        this.activity = activity;
        this.tableItems = tableItems;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.table_item, parent, false);

        tableDate = convertView.findViewById(R.id.dateItem);
        tableReps = convertView.findViewById(R.id.repItem);
        tableWeight = convertView.findViewById(R.id.weightItem);

        WorkoutEntry item = tableItems.get(position);

        tableDate.setText(item.getExerciseDate());
        tableReps.setText(item.getExerciseReps());
        tableWeight.setText(item.getExerciseWeight());

        return convertView;
    }

}

