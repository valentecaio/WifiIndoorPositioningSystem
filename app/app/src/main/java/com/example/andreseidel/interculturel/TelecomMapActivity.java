package com.example.andreseidel.interculturel;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import Dijkstra.engine.DijkstraAlgorithm;
import Dijkstra.model.Edge;
import Dijkstra.model.Graph;
import Dijkstra.model.Vertex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import Dijkstra.engine.*;
import Dijkstra.model.*;
import Dijkstra.test.*;


public class TelecomMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Building> buildings;
    WifiManager mgr;
    WifiInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecom_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildings = new ArrayList<Building>();
        createBuildings(buildings);

    }

    private void createBuildings(List<Building> buildings) {
        PolygonOptions b1 = new PolygonOptions().add(new LatLng(48.358642, -4.570442))
                                            .add(new LatLng(48.358753, -4.570022))
                                            .add(new LatLng(48.358543, -4.569875))
                                            .add(new LatLng(48.358423, -4.570286))
                                            .add(new LatLng(48.358642, -4.570442));
        b1.fillColor(0);
        Building b01 = new Building("B01", b1, new LatLng(48.358555, -4.570028));
        buildings.add(b01);

        PolygonOptions b3 = new PolygonOptions().add(new LatLng(48.358565, -4.570389))
                                                    .add(new LatLng(48.358434, -4.570300))
                                                    .add(new LatLng(48.358314, -4.570740))
                                                    .add(new LatLng(48.358439, -4.570827))
                                                    .add(new LatLng(48.358565, -4.570389));
        b3.fillColor(3332);
        Building b03 = new Building("B03", b3, new LatLng(48.358442, -4.570535));
        buildings.add(b03);

        PolygonOptions a1 = new PolygonOptions().add(new LatLng(48.359213, -4.570206))
                                                .add(new LatLng(48.359346, -4.569758))
                                                .add(new LatLng(48.359241, -4.569681))
                                                .add(new LatLng(48.359112, -4.570138))
                                                .add(new LatLng(48.359213, -4.570206));
        a1.fillColor(3332);
        Building a01 = new Building("A01", a1, new LatLng(48.359184, -4.570039));
        buildings.add(a01);

        PolygonOptions e1 = new PolygonOptions().add(new LatLng(48.358921, -4.570316))
                                                .add(new LatLng(48.359108, -4.569686))
                                                .add(new LatLng(48.358937, -4.569620))
                                                .add(new LatLng(48.358761, -4.570201))
                                                .add(new LatLng(48.358921, -4.570316));
        e1.fillColor(3332);
        Building e01 = new Building("E01", e1, new LatLng(48.358955, -4.569901));
        buildings.add(e01);

        PolygonOptions d3 = new PolygonOptions().add(new LatLng(48.358974, -4.571230))
                                                .add(new LatLng(48.359013, -4.571090))
                                                .add(new LatLng(48.358620, -4.570814))
                                                .add(new LatLng(48.358574, -4.570960))
                                                .add(new LatLng(48.358974, -4.571230));
        d3.fillColor(3332);
        Building d03 = new Building("D03", d3, new LatLng(48.358769, -4.571014));
        buildings.add(d03);

        PolygonOptions d1 = new PolygonOptions().add(new LatLng(48.359193, -4.571061))
                                                .add(new LatLng(48.359289, -4.570750))
                                                .add(new LatLng(48.359018, -4.570571))
                                                .add(new LatLng(48.358933, -4.570880))
                                                .add(new LatLng(48.359193, -4.571061));
        d1.fillColor(3332);
        Building d01 = new Building("D01", d1, new LatLng(48.359107, -4.570807));
        buildings.add(d01);

        PolygonOptions d2 = new PolygonOptions().add(new LatLng(48.359288, -4.570745))
                                                .add(new LatLng(48.359403, -4.570349))
                                                .add(new LatLng(48.359080, -4.570114))
                                                .add(new LatLng(48.359006, -4.570356))
                                                .add(new LatLng(48.359207, -4.570500))
                                                .add(new LatLng(48.359288, -4.570745));
        d1.fillColor(3332);

        Building d02 = new Building("D02", d2, new LatLng(48.359110, -4.570296));
        buildings.add(d02);

        Building e011 = new Building("E01-1", new LatLng(48.358862, -4.570178));
        buildings.add(e011);
        Building b011 = new Building("B01-1", new LatLng(48.358477, -4.570307));
        buildings.add(b011);
        Building b01port = new Building("B01-PORT1", new LatLng(48.358423, -4.570288));
        buildings.add(b01port);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng telecom = new LatLng(48.358613, -4.570223);
        //mMap.addMarker(new MarkerOptions().position(telecom).title("Marker in telecom"));
        float zoomLevel = 17; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telecom, zoomLevel));

        Test t = new Test();
        List<String> way = t.test();
        IO.print(way.toString());

        showBuildingsInOrder(way);

       /*Room location = findYourself();

        IO.print(location.getName());

        Building b = getBuildingFromName(location.getName());

        if(b != null) {
            LatLng locationLatLng = b.getCenter();
            mMap.addMarker(new MarkerOptions().position(locationLatLng).title("You are here"));
        }
        else{
            mMap.addMarker(new MarkerOptions().position(telecom).title("Couldnt find your location"));
        }*/
    }

    public Building getBuildingFromName(String name){
        for(Building b : buildings){
            if(b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }

    public void showBuildingsInOrder(List<String> namesInOrder){
        int i = 1;
        PolylineOptions route = new PolylineOptions();
        for(String name : namesInOrder){
            for (Building b : buildings){
                if(b.getName().equals(name)){
                    if(b.getBoarders() != null) {
                        b.getBoarders().visible(true);
                        mMap.addPolygon(b.getBoarders());
                    }
                    mMap.addMarker(new MarkerOptions().position(b.getCenter()).title("" + i));
                    route.add(b.getCenter());
                    i++;
                }
            }
        }
        mMap.addPolyline(route);
    }

    public Room findYourself(){
        AppFileManager fm = new AppFileManager(this);

        List<Room> rooms = fm.readAllRoomsAsArray();
        info = mgr.getConnectionInfo();
        String bssid = info.getBSSID();
        int rssi = info.getRssi();

        List<RouterInRoom> candidates = new ArrayList<RouterInRoom>();
        List<Integer> candidatesIndexesInRooms = new ArrayList<Integer>();
        Room mostProbable = null;

        int index = 0;
        for (Room room : rooms){
            for(RouterInRoom r : room.getRouters()){
                if(r.getBssid().equals(bssid)){
                    candidates.add(r);
                    candidatesIndexesInRooms.add(index);
                }
            }
            index++;
        }

        float minDif = 1000;
        int i = 0;

        for(RouterInRoom candidate : candidates){
            float dif = rssi - candidate.getMean();
            if (dif < minDif){
                minDif = dif;
                mostProbable = rooms.get(candidatesIndexesInRooms.get(i));
            }
            i++;
        }
        return mostProbable;
    }
}
