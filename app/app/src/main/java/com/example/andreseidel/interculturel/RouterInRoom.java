package com.example.andreseidel.interculturel;

/**
 * Created by AndreSeidel on 07/12/16.
 */

public class RouterInRoom {
    private int sumSamples;
    private int nSamples;
    private float mean;
    private String bssid;

    // construtor to get data from CSV
    public RouterInRoom(String bssid, int sumSamples, int nSamples) {
        this.sumSamples = sumSamples;
        this.nSamples = nSamples;
        this.bssid = bssid;
    }

    public RouterInRoom (String bssid, int rssi){
        this.bssid = bssid;
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
        return bssid;
    }
    
    public int getSumSamples() {
        return sumSamples;
    }

    public void setSumSamples(int sumSamples) {
        this.sumSamples = sumSamples;
    }

    public int getnSamples() {
        return nSamples;
    }

    public void setnSamples(int nSamples) {
        this.nSamples = nSamples;
    }

    public float getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String toCSV(){
        return this.getBssid() + ',' + this.getSumSamples() + ',' + this.getnSamples();
    }



}
