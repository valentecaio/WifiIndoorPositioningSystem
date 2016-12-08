package com.example.andreseidel.interculturel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);
        IO.print(readAll().toString());
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
