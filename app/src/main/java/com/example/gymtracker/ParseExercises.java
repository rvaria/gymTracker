package com.example.gymtracker;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseExercises extends AppCompatActivity {

    private Activity context;
    private String name;
    private String type;
    private String muscle;
    private List<String> exerciseNames;

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
                exerciseNames.add(exerciseObject.get("name").toString());
            }
        }

       return exerciseNames;

    }

}
