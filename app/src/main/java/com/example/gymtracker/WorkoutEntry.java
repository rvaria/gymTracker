package com.example.gymtracker;

public class WorkoutEntry {

    private String exerciseDate;
    private String exerciseReps;
    private String exerciseWeight;

    public WorkoutEntry(String exerciseDate, String exerciseReps, String exerciseWeight) {

        this.exerciseDate = exerciseDate;
        this.exerciseReps = exerciseReps;
        this.exerciseWeight = exerciseWeight;

    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public String getExerciseReps() {
        return exerciseReps;
    }

    public String getExerciseWeight() {
        return exerciseWeight;
    }

}
