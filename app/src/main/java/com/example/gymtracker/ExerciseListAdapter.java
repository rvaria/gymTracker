package com.example.gymtracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExerciseListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<String> exerciseItems;
    private List<String> displayItems;
    private List<String> finalList;
    private TextView exerciseName;
    private CheckBox checkBox;
    private String item;


    public ExerciseListAdapter(Activity activity, List<String> exerciseItems) {
        super(activity ,R.layout.exercise_item, exerciseItems);
        this.activity = activity;
        this.exerciseItems = exerciseItems;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.exercise_item, parent, false);

        exerciseName = convertView.findViewById(R.id.exerciseName);
        checkBox = convertView.findViewById(R.id.checkBox);

        item = exerciseItems.get(position);
        exerciseName.setText(item);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String exerciseQuery = constraint.toString().toLowerCase();
                FilterResults filterResults = new FilterResults();
                displayItems = new ArrayList<String>();

                if(exerciseQuery.length() > 0) {
                    for(String filter : exerciseItems) {
                        if(filter.toLowerCase().contains(exerciseQuery)) {
                            displayItems.add(filter);

                        }
                    }
                }
                filterResults.count = displayItems.size();
                filterResults.values = displayItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                finalList = (List<String>) results.values;

            }
        };
    }
}
