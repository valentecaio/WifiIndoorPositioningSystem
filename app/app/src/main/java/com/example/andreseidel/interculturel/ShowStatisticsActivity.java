package com.example.andreseidel.interculturel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowStatisticsActivity extends AppCompatActivity {
    TextView wifiDataTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);
        wifiDataTV = (TextView) findViewById(R.id.wifiDataTV);

        AppFileManager fileMgr = new AppFileManager(this);
        ArrayList<Room> rooms = fileMgr.readAllRoomsAsArray();
        String str = "";
        for(Room r: rooms){
            str += r + "\n";
        }
        wifiDataTV.setText(str);

        IO.print(str);
        IO.print(rooms.toString());
    }

}
