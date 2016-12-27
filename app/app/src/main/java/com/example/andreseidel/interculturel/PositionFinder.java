package com.example.andreseidel.interculturel;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caio on 27/12/2016.
 */

public class PositionFinder {
    WifiManager mgr;
    Activity delegate;

    public PositionFinder(Activity delegate) {
        this.delegate = delegate;

        // gets wifi manager before find the location
        mgr = (WifiManager) delegate.getSystemService(Context.WIFI_SERVICE);
    }

    public Room findYourself(){
        try {
            AppFileManager fm = new AppFileManager(this.delegate);

            List<Room> rooms = fm.readAllRoomsAsArray();
            WifiInfo info = mgr.getConnectionInfo();
            String bssid = info.getBSSID();
            int rssi = info.getRssi();

            List<RouterInRoom> candidates = new ArrayList<RouterInRoom>();
            List<Integer> candidatesIndexesInRooms = new ArrayList<Integer>();
            Room mostProbable = null;

            int index = 0;
            for (Room room : rooms) {
                for (RouterInRoom r : room.getRouters()) {
                    if (r.getBssid().equals(bssid)) {
                        candidates.add(r);
                        candidatesIndexesInRooms.add(index);
                    }
                }
                index++;
            }

            float minDif = 1000;
            int i = 0;

            for (RouterInRoom candidate : candidates) {
                float dif = rssi - candidate.getMean();
                if (dif < minDif) {
                    minDif = dif;
                    mostProbable = rooms.get(candidatesIndexesInRooms.get(i));
                }
                i++;
            }
            return mostProbable;
        } catch (Exception e) {
            return null;
        }
    }
}
