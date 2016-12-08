package com.example.andreseidel.interculturel;

/**
 * Created by AndreSeidel on 01/12/16.
 */

public class Router {
    private String id;
    private int rssi;

    public Router(String id, int rssi){
        this.id = id;
        this.rssi = rssi;
    }

    void setId(String id){
        this.id = id;
    }
    void setRssi(int rssi){
        this.rssi = rssi;
    }

    String getId(){
        return this.id;
    }

    int getRssi(){
        return this.rssi;
    }
}
