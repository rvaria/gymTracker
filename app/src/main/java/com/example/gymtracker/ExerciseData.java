package com.example.gymtracker;

public class ExerciseData {

    private String exerciseName;
    private String reps;
    private String weight;

    public ExerciseData(String exerciseName) {
        this.exerciseName = exerciseName;
        this.reps = "";
        this.weight = "";

    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getReps() {
        return reps;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }
}
