package com.example.gymtracker;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProgressGraph extends Fragment {

    private TextView graphName;
    private List<WorkoutEntry> exerciseData;
    private ExerciseDatabase exerciseDatabase;
    private ExerciseProgressActivity exerciseProgressActivity;
    private Spinner weightChoice;
    private LineChart chart;
    private List<String> date;
    private List<String> weight;
    private List<Entry> reps;
    private LineDataSet repAxisSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name = bundle.getString("exerciseName");

        View view = inflater.inflate(R.layout.fragment_progress_graph, container, false);

        weightChoice = view.findViewById(R.id.weightChoice);
        graphName = view.findViewById(R.id.graphName);
        chart = view.findViewById(R.id.progressChart);
        graphName.setText(name);
        exerciseDatabase = new ExerciseDatabase(getContext());
        exerciseData = new ArrayList<>(exerciseDatabase.getData(name));

        exerciseProgressActivity = new ExerciseProgressActivity();
        exerciseProgressActivity.sortDate(exerciseData, "Oldest");

        date = new ArrayList<>();
        reps = new ArrayList<>();
        weight = new ArrayList<>();

        for(WorkoutEntry workoutEntry : exerciseData) {
            date.add(workoutEntry.getExerciseDate());
            if(!weight.contains(workoutEntry.getExerciseWeight())) {
                weight.add(workoutEntry.getExerciseWeight());
            }
        }

        for(int i = 0; i < exerciseData.size(); i++) {
            WorkoutEntry repEntry = exerciseData.get(i);
            reps.add(new BarEntry(i, Float.parseFloat(repEntry.getExerciseReps())));
        }

        ArrayAdapter<String> weightDropdown = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, weight);
        weightDropdown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightChoice.setAdapter(weightDropdown);

        weightChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosenOrder = weightChoice.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        repAxisSet = new LineDataSet(reps, "Reps");
        repAxisSet.setColor(Color.BLUE);
        LineData chartData = new LineData(repAxisSet);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(60);
        xAxis.setGranularity(1f);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(date));
        YAxis repAxis = chart.getAxisLeft();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        repAxis.setDrawAxisLine(true);

        repAxis.setTextColor(Color.WHITE);

        chart.setDoubleTapToZoomEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);

        chart.setData(chartData);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);

        return view;
    }
}