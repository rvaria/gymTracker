package com.example.gymtracker;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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
    private WorkoutEntry workoutEntry;
    private LineChart chart;
    private List<String> date;
    private List<Entry> reps;
    private List<Entry> weight;
    private LineDataSet repAxisSet;
    private LineDataSet weightAxisSet;

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

        exerciseProgressActivity = new ExerciseProgressActivity();
        exerciseProgressActivity.sortDate(exerciseData, "ascending");

        chart = view.findViewById(R.id.progressChart);

        date = new ArrayList<>();
        reps = new ArrayList<>();
        weight = new ArrayList<>();

        for(WorkoutEntry workoutEntry : exerciseData) {
            date.add(workoutEntry.getExerciseDate());
        }

        for(int i = 0; i < exerciseData.size(); i++) {
            WorkoutEntry repEntry = exerciseData.get(i);
            reps.add(new Entry(i, Float.parseFloat(repEntry.getExerciseReps())));
            weight.add(new Entry(i, Float.parseFloat(repEntry.getExerciseWeight())));
        }

        repAxisSet = new LineDataSet(reps, "Reps");
        weightAxisSet = new LineDataSet(weight, "Weight");
        weightAxisSet.setColor(Color.RED);
        LineData chartData = new LineData(repAxisSet, weightAxisSet);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(60);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(date));
        YAxis repAxis = chart.getAxisLeft();
        xAxis.setTextColor(Color.WHITE);
        YAxis weightAxis = chart.getAxisRight();
        xAxis.setDrawAxisLine(true);
        repAxis.setDrawAxisLine(true);
        weightAxis.setDrawAxisLine(true);

        repAxis.setTextColor(Color.WHITE);
//        weightAxis.setTextColor(Color.WHITE);

        chart.setDoubleTapToZoomEnabled(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.setData(chartData);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);

        return view;
    }
}