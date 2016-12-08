package com.example.andreseidel.interculturel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;

import java.util.List;

import java.util.ArrayList;

public class InitialPageActivity extends AppCompatActivity {
    EditText roomName;
    Button getStats;
    Button setStats;
    AppFileManager fileManager;

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        setStats = (Button) findViewById(R.id.setStats);
        getStats = (Button) findViewById(R.id.getStats);

        fileManager = new AppFileManager(this, "arquivo-teste");
    }

    public void getStats(View view) {
        getStats.setText(fileManager.getPath());
        /*
        Intent intent = new Intent(this, ShowStatisticsActivity.class);
        startActivity(intent);
        */
    }

    public void setStats(View view) {
        Intent intent = new Intent(this, LearningActivity.class);
        startActivity(intent);
    }

    public void getMap(View view) {
        Intent intent = new Intent(this, TelecomMapActivity.class);
        startActivity(intent);
    }
}
