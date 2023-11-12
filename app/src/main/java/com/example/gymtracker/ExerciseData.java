package com.example.gymtracker;

public class ExerciseData {

    private String exerciseName;
    private String reps;
    private String weight;
    private String position;

    public ExerciseData(String exerciseName) {
        this.exerciseName = exerciseName;
        this.reps = "";
        this.weight = "";
        this.position = "";

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

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
