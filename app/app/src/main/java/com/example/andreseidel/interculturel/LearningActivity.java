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
    List<RouterInRoom> routerInRoom;
    List<Room> rooms;
    AppFileManager fileManager;
    Room room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        tv = (TextView) findViewById(R.id.wifiStrength);
        mgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        rooms = new ArrayList<Room>();
        //Bundle bundle = getIntent().getExtras();
        //String message = bundle.getString("message");
        //room = new Room(message);
    }

    public void Back(View view) {
        Intent intent2 = new Intent(this, InitialPageActivity.class);
        startActivity(intent2);
    }

    public void setName(View view) {
        EditText edText = (EditText)findViewById(R.id.roomName);
        String roomName = edText.getText().toString();

        Room rm = new Room(roomName);
        rooms.add(rm);
    }

    public void Retry(View view) {
        try {
            info = mgr.getConnectionInfo();

            //String data3 = wifiReciever.toString();

            //String data2 = wifiScanList.get(0).toString();

            String bssid = info.getBSSID();
            int rssi = info.getRssi();
            RouterInRoom r = new RouterInRoom(bssid, rssi);

            rooms.get(rooms.size() - 1).add(r);

            ((TextView)findViewById(R.id.wifiStrength)).setText(rooms.toString());
        }
        catch (Exception e){
            tv.setText("" + e.getLocalizedMessage());
        }
    }
}
