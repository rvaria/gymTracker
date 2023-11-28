package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ExerciseProgressActivity extends AppCompatActivity {

    private TabLayout chooseTab;
    private ViewPager2 viewPager;
    private ViewPageAdapter viewPageAdapter;
    private ExerciseDatabase exerciseDatabase;
    private List<WorkoutEntry> exerciseData;
    private String routine;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_progress);

        chooseTab = findViewById(R.id.progressTabs);
        viewPager = findViewById(R.id.viewPager);

        exerciseDatabase = new ExerciseDatabase(this);
        viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);

        new TabLayoutMediator(chooseTab, viewPager, (tab, position) -> {
            switch(position) {
                case 0:
                    tab.setText("TABLE");
                case 1:
                    tab.setText("GRAPH");
            }
        }).attach();

        Intent getRoutine = getIntent();
        routine = getRoutine.getExtras().getString("exercise");

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

        exerciseData = new ArrayList<>(exerciseDatabase.getData(routine));

    }
}
