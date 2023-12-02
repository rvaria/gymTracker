package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ExerciseProgressActivity extends AppCompatActivity {

    private TabLayout chooseTab;
    private ViewPager2 viewPager;
    private ViewPageAdapter viewPageAdapter;
    private ExerciseDatabase exerciseDatabase;
    private String exercise;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_progress);

        chooseTab = findViewById(R.id.progressTabs);
        viewPager = findViewById(R.id.viewPager);

        Intent getRoutine = getIntent();
        exercise = getRoutine.getExtras().getString("exercise");

        exerciseDatabase = new ExerciseDatabase(this);
        viewPageAdapter = new ViewPageAdapter(this);
        viewPageAdapter.passData(exercise);
        viewPager.setAdapter(viewPageAdapter);

        new TabLayoutMediator(chooseTab, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("TABLE");
                    break;
                case 1:
                    tab.setText("GRAPH");
                    break;
            }
        }).attach();

        chooseTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void sortDate(List<WorkoutEntry> exerciseData, String order) {

        DateFormat date = new SimpleDateFormat("dd/mm/yyyy");
        Collections.sort(exerciseData, new Comparator<WorkoutEntry>() {
            @Override
            public int compare(WorkoutEntry o1, WorkoutEntry o2) {
                try {
                    Date date1 = date.parse(o1.getExerciseDate());
                    Date date2 = date.parse(o2.getExerciseDate());
                    if (order == "ascending") {
                        return date1.compareTo(date2);
                    } else {
                        return date2.compareTo(date1);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });

    }
}
