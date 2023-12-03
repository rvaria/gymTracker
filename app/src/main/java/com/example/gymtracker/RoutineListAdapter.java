package com.example.gymtracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class RoutineListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<String> routineItems;
    private TextView routine;
    private Button deleteRoutine;
    private String item;
    private ExerciseDatabase exerciseDatabase;

    public RoutineListAdapter(Activity activity, List<String> routineItems) {
        super(activity, R.layout.view_routine_item, routineItems);
        this.activity = activity;
        this.routineItems = routineItems;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(R.layout.view_routine_item, parent, false);

        routine = convertView.findViewById(R.id.routineName);
        deleteRoutine = convertView.findViewById(R.id.deleteRoutine);

        item = routineItems.get(position);
        routine.setText(item);

        deleteRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDelete(routineItems.get(position));
            }
        });

        return convertView;

    }

    public void alertDelete(String routineName) {

        AlertDialog.Builder createBox = new AlertDialog.Builder(getContext());
        createBox.setTitle("Are you sure you want to delete " + routineName + "?");

        createBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exerciseDatabase = new ExerciseDatabase(getContext());
                exerciseDatabase.deleteRoutine(routineName);
                notifyDataSetChanged();
            }
        });

        createBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        createBox.show();

    }
}