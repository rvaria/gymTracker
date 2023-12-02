package com.example.gymtracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ProgressTable extends Fragment {

    private TextView tableName;
    private List<WorkoutEntry> exerciseData;
    private ExerciseDatabase exerciseDatabase;
    private ExerciseProgressActivity exerciseProgressActivity;
    private ListView tableList;
    private TableListAdapter tableListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name = bundle.getString("exerciseName");

        View view = inflater.inflate(R.layout.fragment_progress_table, container, false);

        tableName = view.findViewById(R.id.tableName);
        tableName.setText(name);

        exerciseDatabase = new ExerciseDatabase(getContext());
        exerciseData = new ArrayList<>(exerciseDatabase.getData(name));

        exerciseProgressActivity = new ExerciseProgressActivity();
        exerciseProgressActivity.sortDate(exerciseData, "descending");

        tableList = view.findViewById(R.id.tableList);
        tableListAdapter = new TableListAdapter(getActivity(), exerciseData);
        tableList.setAdapter(tableListAdapter);

        return view;

    }
}