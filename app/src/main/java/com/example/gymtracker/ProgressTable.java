package com.example.gymtracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ProgressTable extends Fragment {

    private TextView tableName;
    private List<WorkoutEntry> exerciseData;
    private ExerciseDatabase exerciseDatabase;
    private ExerciseProgressActivity exerciseProgressActivity;
    private Spinner order;
    private String[] orderChoice;
    private ListView tableList;
    private TableListAdapter tableListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name = bundle.getString("exerciseName");

        orderChoice = new String[] {"Newest", "Oldest"};

        View view = inflater.inflate(R.layout.fragment_progress_table, container, false);

        order = view.findViewById(R.id.orderFilter);
        tableList = view.findViewById(R.id.tableList);
        tableName = view.findViewById(R.id.tableName);
        tableName.setText(name);

        exerciseDatabase = new ExerciseDatabase(getContext());
        exerciseData = new ArrayList<>(exerciseDatabase.getData(name));

        exerciseProgressActivity = new ExerciseProgressActivity();

        ArrayAdapter<String> dropDown = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, orderChoice);
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        order.setAdapter(dropDown);

        order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosenOrder = order.getSelectedItem().toString();
                if(chosenOrder.equals("Newest")) {
                    exerciseProgressActivity.sortDate(exerciseData, "Newest");
                    setTableList(exerciseData);

                } else {
                    exerciseProgressActivity.sortDate(exerciseData, "Oldest");
                    tableList.setAdapter(tableListAdapter);
                    setTableList(exerciseData);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        return view;

    }

    public void setTableList(List<WorkoutEntry> tableData) {
        tableListAdapter = new TableListAdapter(getActivity(), tableData);
        tableList.setAdapter(tableListAdapter);
    }
}