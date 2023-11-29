package com.example.gymtracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProgressGraph extends Fragment {

    private TextView graphName;
    private List<WorkoutEntry> exerciseData;
    private ExerciseDatabase exerciseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name = bundle.getString("exerciseName");

        View view = inflater.inflate(R.layout.fragment_progress_graph, container, false);

        graphName = view.findViewById(R.id.graphName);
        graphName.setText(name);
        exerciseDatabase = new ExerciseDatabase(getContext());
        exerciseData = new ArrayList<>(exerciseDatabase.getData(name));

        return view;
    }
}