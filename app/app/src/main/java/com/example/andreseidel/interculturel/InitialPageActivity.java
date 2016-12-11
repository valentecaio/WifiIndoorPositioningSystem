package com.example.andreseidel.interculturel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InitialPageActivity extends AppCompatActivity {
    EditText roomName;
    Button getStats;
    Button setStats;

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        setStats = (Button) findViewById(R.id.setStats);
        getStats = (Button) findViewById(R.id.getStats);
    }

    public void getStats(View view) {
        Intent intent = new Intent(this, ShowStatisticsActivity.class);
        startActivity(intent);
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
