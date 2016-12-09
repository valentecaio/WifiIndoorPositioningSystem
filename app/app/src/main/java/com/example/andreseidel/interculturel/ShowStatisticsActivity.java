package com.example.andreseidel.interculturel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowStatisticsActivity extends AppCompatActivity {
    TextView wifiDataTV;
    private ListView mListView;

    private ArrayList<Room> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);

        // read data
        AppFileManager fileMgr = new AppFileManager(this);
        this.rooms = fileMgr.readAllRoomsAsArray();
        IO.print(rooms.toString());

        //this.loadDataInTextView();

        this.loadDataInListView();
    }

    public void loadDataInListView(){
        mListView = (ListView) findViewById(R.id.statistics_list_view);
        String[] listItems = new String[rooms.size()];
        wifiDataAdapter adapter = new wifiDataAdapter(this, rooms);
        mListView.setAdapter(adapter);
    }

    public void loadDataInTextView(){
        wifiDataTV = (TextView) findViewById(R.id.wifiDataTV);
        String str = "";
        for(Room r: rooms){
            IO.print(r.toString());
            str += r + "\n";
        }
        wifiDataTV.setText(str);
    }

}
