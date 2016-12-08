package com.example.andreseidel.interculturel;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.content.Context;

import java.util.List;


public class learningActivity extends AppCompatActivity {
    WifiManager mgr;
    WifiInfo info;
    TextView tv;
    List<RouterInRoom> routerInRoom;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        tv = (TextView) findViewById(R.id.wifiStrength);
        mgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    }

    public void Back(View view) {
        Intent intent = new Intent(this, showRoom.class);
        startActivity(intent);
    }

    public void Retry(View view) {
        try {
            info = mgr.getConnectionInfo();

            //String data3 = wifiReciever.toString();

            //String data2 = wifiScanList.get(0).toString();

            String bssid = info.getBSSID();
            int rssi = info.getRssi();
            RouterInRoom r = new RouterInRoom(bssid, rssi);

            routerInRoom.add(r);
            //String data5 = wifiReciever.getResultData();
        }
        catch (Exception e){
            tv.setText(""+ e.getLocalizedMessage());
        }
    }
}
