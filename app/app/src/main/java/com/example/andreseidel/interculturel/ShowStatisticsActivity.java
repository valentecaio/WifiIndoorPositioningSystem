package com.example.andreseidel.interculturel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowStatisticsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView wifiDataTV;
    private ListView mListView;
    private ArrayList<Room> rooms;
    private AppFileManager fileMgr;
    private wifiDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);

        this.fileMgr = new AppFileManager(this);

        // read data
        this.readData();;

        //this.loadDataInTextView();
        this.loadDataInListView();
    }

    public void loadDataInListView(){
        mListView = (ListView) findViewById(R.id.statistics_list_view);
        this.adapter = new wifiDataAdapter(this, rooms);
        this.mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);
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

    public void askConfirmationAndDelete(final String fileName){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning")
                .setMessage("Are you sure you want to delete this place?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fileMgr.setName(fileName);
                        fileMgr.deleteFile();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Room roomToDelete = (Room)mListView.getItemAtPosition(position);
        //IO.print(roomToDelete.getName().toString());
        askConfirmationAndDelete(roomToDelete.getName());
        this.updateListView();
    }

    private void updateListView(){
        this.readData();
        this.adapter.updateRoomsList(this.rooms);
    }

    public void readData(){
        AppFileManager fileMgr = new AppFileManager(this);
        this.rooms = fileMgr.readAllRoomsAsArray();
        IO.print(rooms.toString());
    }
}
