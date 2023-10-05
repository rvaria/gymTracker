package com.example.gymtracker;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParseExercises {

    private String name;
    private String type;
    private String muscle;

    public ParseExercises(String name, String type, String muscle) {
        this.name = name;
        this.type = type;
        this.muscle = muscle;
    }

    public void readFile() throws FileNotFoundException {

        JsonReader reader = new JsonReader(new FileReader("exercisesyuhonas.json"));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


    }

}
