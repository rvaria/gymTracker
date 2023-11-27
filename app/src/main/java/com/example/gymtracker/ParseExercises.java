package com.example.gymtracker;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParseExercises extends AppCompatActivity {

    private Activity context;
    private String name;
    private List<String> exerciseNames;
    private List<String> muscleGroup;
    private List<String> muscleExercises;

    public ParseExercises(Activity context) {
        this.context = context;
    }


    public String convertFile() throws IOException {

        InputStream jsonFileStream = context.getResources().openRawResource(R.raw.exercisesyuhonas);
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonFileStream));

        StringBuilder jsonFile = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonFile.append(line);
        }

        return jsonFile.toString();

    }

    public List readJson(String fileContents) {

        //Parsing string representation of JSON file into a JSON element which is
        //then converted into a JSON array
        JsonElement jsonElement = JsonParser.parseString(fileContents);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        exerciseNames = new ArrayList<>();

        //Iterating through JSON array to extract specific values from each JSON object
        for(JsonElement exercise : jsonArray) {
            JsonObject exerciseObject = exercise.getAsJsonObject();
            String type = exerciseObject.get("category").toString();
            if(type.equals("\"strength\"")) {
                name = exerciseObject.get("name").toString();
                exerciseNames.add(name.replaceAll("\"", ""));
            }
        }

       return exerciseNames;

    }

    public List filterMuscle(String fileContents, List<String> muscles) {

        JsonElement jsonElement = JsonParser.parseString(fileContents);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        muscleGroup = new ArrayList<>();
        muscleExercises = new ArrayList<>();

        for(JsonElement exercise : jsonArray) {
            JsonObject exerciseObject = exercise.getAsJsonObject();
            String type = exerciseObject.get("category").toString();
            if(type.equals("\"strength\"")) {
                String muscle = exerciseObject.get("primaryMuscles").toString();
                muscle = muscle.replaceAll("[^a-z]", "");
                for(String muscleName : muscles) {
                    if(muscle.equalsIgnoreCase(muscleName)) {
                        name = exerciseObject.get("name").toString();
                        muscleExercises.add(name.replaceAll("\"", ""));
                    }
                }
            }
        }

        return muscleExercises;

    }

}
