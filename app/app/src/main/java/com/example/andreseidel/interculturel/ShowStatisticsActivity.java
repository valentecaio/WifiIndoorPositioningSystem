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

        ArrayList<Room> rooms = readAll();
        String str = "";
        for(Room r: rooms){
            str += r + "\n";
        }
        wifiDataTV.setText(str);
    }

    public ArrayList<Room> readAll(){
        // read all files
        AppFileManager fileMgr = new AppFileManager(this);
        ArrayList<String> roomsCSV = fileMgr.readAll();

        // instantiate all rooms
        ArrayList<Room> rooms = new ArrayList<Room>();
        for(String roomCSV: roomsCSV){
            rooms.add(fileMgr.roomFromCSV(roomCSV));
        }
        return rooms;
    }

}
