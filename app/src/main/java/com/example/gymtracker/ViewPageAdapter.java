package com.example.gymtracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {

    private String exerciseName;

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void passData(String data) {
        exerciseName = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("exerciseName", exerciseName);

        switch(position) {
            case 0:
            default:
                ProgressTable progressTable = new ProgressTable();
                progressTable.setArguments(bundle);
                return progressTable;
            case 1:
                ProgressGraph progressGraph = new ProgressGraph();
                progressGraph.setArguments(bundle);
                return progressGraph;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
