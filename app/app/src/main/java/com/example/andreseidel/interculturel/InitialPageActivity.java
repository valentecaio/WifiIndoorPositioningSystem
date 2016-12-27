package com.example.andreseidel.interculturel;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class InitialPageActivity extends AppCompatActivity {
    EditText roomName;
    Button getStats;
    Button setStats;
    int countCristofe = 0;

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        setStats = (Button) findViewById(R.id.setStats);
        getStats = (Button) findViewById(R.id.getStats);

        PositionFinder finder = new PositionFinder(this);
        Room location = finder.findYourself();
        if (location != null) {
            this.roomName.setText("you are in: " + location.getName());
        }

    }

    public void onClickTestView(View view){
        if (countCristofe++ >= 10) {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.cristofe);
            String str = "cristofe trou de cul";
            // confirmation dialog
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setView(image)
                    .setTitle("-")
                    .setMessage(str)
                    .setNegativeButton("Ok", null)
                    .show();
            // reset counter
            countCristofe = 0;
        }
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

    public void exportData(View view){
        IO.print("export data");
        AppFileManager fileMgr = new AppFileManager(this);

        try {
            // ask permission
            askExportPermission();

            // export files
            String exportedFile = fileMgr.exportAll("Interculturel");

            // confirmation dialog
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Data exported")
                    .setMessage("Your data was exported to: " + exportedFile)
                    .setNegativeButton("Ok", null)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askExportPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

}
