package com.example.andreseidel.interculturel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by AndreSeidel on 08/12/16.
 */

public class Building {
    private String name;
    private PolygonOptions boarders;
    private LatLng center;


    public Building(String name, PolygonOptions b) {
        this.name = name;
        this.boarders = b;
    }

    public Building(String name, LatLng center) {
        this.name = name;
        this.center = center;
    }

    public Building(String name, PolygonOptions boarders, LatLng center) {
        this.name = name;
        this.boarders = boarders;
        this.center = center;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PolygonOptions getBoarders() {
        return boarders;
    }

    public void setBoarders(PolygonOptions boarders) {
        this.boarders = boarders;
    }
}
