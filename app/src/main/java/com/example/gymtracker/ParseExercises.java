package com.example.gymtracker;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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

    public void readJson(String file) {

        Gson gson = new Gson();
        System.out.println(file);

        ParseExercises newObj = gson.fromJson(file, ParseExercises.class);

    }

}
