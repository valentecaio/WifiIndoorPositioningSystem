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

<<<<<<< HEAD
    public String toString(){
        return name;
    }
=======
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
>>>>>>> e6f76d73128be134503d5960f2cc3db234d3bbd0
}
