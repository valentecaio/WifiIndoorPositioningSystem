package com.example.andreseidel.interculturel;

/**
 * Created by AndreSeidel on 07/12/16.
 */

public class RouterInRoom {
    private int sumSamples;
    private int nSamples;
    private float mean;
    private String name;

    public RouterInRoom (String name, int rssi){
        this.name = name;
        this.sumSamples = rssi;
        this.nSamples = 1;
        this.mean = sumSamples/nSamples;
    }

    public float add(RouterInRoom rout){
        sumSamples = sumSamples + rout.sumSamples;
        nSamples = nSamples + 1;
        mean = sumSamples/nSamples;
        return mean;
    }

    public String toString(){
        return name;
    }
}
