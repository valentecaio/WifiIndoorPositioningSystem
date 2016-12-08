package com.example.andreseidel.interculturel;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends AppCompatActivity {
    WifiManager mgr;
    WifiInfo info;
    TextView tv;
    EditText roomName;
    Room room = new Room();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learning);
        tv = (TextView) findViewById(R.id.wifiStrength);
        mgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        roomName = (EditText) findViewById(R.id.roomName);

        //Bundle bundle = getIntent().getExtras();
        //String message = bundle.getString("message");
        //room = new Room(message);
    }

    public void Back(View view) {
        Intent intent2 = new Intent(this, InitialPageActivity.class);
        startActivity(intent2);
    }

    public void saveRoomData(View view) throws RoomWithoutNameException {
        // gets room name
        String name = roomName.getText().toString();
        this.room.setName(name);

        // exception if name is invalid
        if(this.room.getName() == null || this.room.getName().isEmpty()){
            throw new RoomWithoutNameException();
        }

        String dataToWrite = this.room.toCSV();
        IO.print(this, roomName.getText().toString());
        IO.print(dataToWrite);

        AppFileManager fileManager = new AppFileManager(this, this.room.getName());
        fileManager.write(dataToWrite);
    }

    public void Retry(View view) {
        try {
            info = mgr.getConnectionInfo();
            //String data3 = wifiReciever.toString();
            //String data2 = wifiScanList.get(0).toString();
            String bssid = info.getBSSID();
            int rssi = info.getRssi();

            RouterInRoom r = new RouterInRoom(bssid, rssi);
            room.add(r);

            tv.setText(room.toString());
        }
        catch (Exception e){
            tv.setText("" + e.getLocalizedMessage());
        }
    }

    private void saveRoomsInfo() throws RoomWithoutNameException {
    }
}
