package com.example.gymtracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ExerciseListAdapter extends ArrayAdapter<String> implements Filterable {

    private Activity activity;
    private List<String> exerciseItems;
    private List<String> originalItems;
    private List<String> displayItems;
    private TextView exerciseName;
    private CheckBox checkBox;
    private String item;
    private int count;

    public ExerciseListAdapter(Activity activity, List<String> exerciseItems) {
        super(activity, R.layout.exercise_item, exerciseItems);
        this.activity = activity;
        this.exerciseItems = exerciseItems;
        this.originalItems = new ArrayList<>(exerciseItems);
        count = 0;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.exercise_item, parent, false);

        exerciseName = convertView.findViewById(R.id.exerciseName);
        checkBox = convertView.findViewById(R.id.checkBox);

        item = exerciseItems.get(position);
        exerciseName.setText(item);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ExercisePopupActivity exercisePopupActivity = (ExercisePopupActivity) activity;
                if (isChecked) {
                    count++;
                    exercisePopupActivity.setItems(exerciseItems.get(position));
                    exercisePopupActivity.setCount(count);
                } else {
                    count--;
                    exercisePopupActivity.removeItems(exerciseItems.get(position));
                    exercisePopupActivity.setCount(count);
                }
            }
        });

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

                if (exerciseQuery.isEmpty()) {
                    displayItems.addAll(originalItems);
                } else {
                    displayItems.clear();
                    for (String filter : exerciseItems) {
                        if (filter.toLowerCase().contains(exerciseQuery)) {
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
                List<String> filteredItems = (List<String>) results.values;
                exerciseItems.clear();
                exerciseItems.addAll(filteredItems);
                notifyDataSetChanged();
                // System.out.println("ITEMS IN THE LIST - " + exerciseItems);

            }
        };
    }
}
