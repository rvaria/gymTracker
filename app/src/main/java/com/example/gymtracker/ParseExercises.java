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

public class ParseExercises extends AppCompatActivity {

    private Activity context;
    private String name;
    private String type;
    private String muscle;

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

    public void readJson(String fileContents) {

        //
        JsonElement jsonElement = JsonParser.parseString(fileContents);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for(JsonElement exerciseElement : jsonArray) {
            JsonObject exerciseObject = exerciseElement.getAsJsonObject();
            String type = exerciseObject.get("category").toString();
            if(type.equals("\"strength\"")) {
                System.out.println(exerciseObject.get("name"));
            }
        }

    }

}
