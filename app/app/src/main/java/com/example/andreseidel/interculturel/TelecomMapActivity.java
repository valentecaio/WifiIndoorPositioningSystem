package com.example.andreseidel.interculturel;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.Marker;

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
    List <Polyline> polylines;
    List <Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecom_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildings = new ArrayList<Building>();
        mgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        polylines = new ArrayList<Polyline>();
        markers = new ArrayList<Marker>();

    }

    private void createBuildings(List<Building> buildings) {
        Building b01 = new Building("B01", new LatLng(48.358555, -4.570028));
        buildings.add(b01);
        markers.add(mMap.addMarker(new MarkerOptions().position(b01.getCenter()).title(b01.getName())));

        Building b03 = new Building("B03", new LatLng(48.358442, -4.570535));
        buildings.add(b03);
        markers.add(mMap.addMarker(new MarkerOptions().position(b03.getCenter()).title(b03.getName())));

        Building a01 = new Building("A01", new LatLng(48.359184, -4.570039));
        buildings.add(a01);
        markers.add(mMap.addMarker(new MarkerOptions().position(a01.getCenter()).title(a01.getName())));

        Building e01 = new Building("E01", new LatLng(48.358955, -4.569901));
        buildings.add(e01);
        markers.add(mMap.addMarker(new MarkerOptions().position(e01.getCenter()).title(e01.getName())));

        Building d03 = new Building("D03", new LatLng(48.358655, -4.570931));
        buildings.add(d03);
        markers.add(mMap.addMarker(new MarkerOptions().position(d03.getCenter()).title(d03.getName())));

/*
        PolygonOptions d1 = new PolygonOptions().add(new LatLng(48.359193, -4.571061))
                                                .add(new LatLng(48.359289, -4.570750))
                                                .add(new LatLng(48.359018, -4.570571))
                                                .add(new LatLng(48.358933, -4.570880))
                                                .add(new LatLng(48.359193, -4.571061));
        d1.fillColor(3332);
*/
        Building d01 = new Building("D01", new LatLng(48.359107, -4.570807));
        buildings.add(d01);
        markers.add(mMap.addMarker(new MarkerOptions().position(d01.getCenter()).title(d01.getName())));

        Building d02 = new Building("D02", new LatLng(48.359110, -4.570296));
        buildings.add(d02);
        markers.add(mMap.addMarker(new MarkerOptions().position(d02.getCenter()).title(d02.getName())));

        Building e011 = new Building("E01-1", new LatLng(48.358862, -4.570178));
        buildings.add(e011);
        Building b011 = new Building("B01-1", new LatLng(48.358477, -4.570307));
        buildings.add(b011);
        Building b01port = new Building("B01-PORT1", new LatLng(48.358423, -4.570288));
        buildings.add(b01port);
        Building d03port1 = new Building("D03-PORT1", new LatLng(48.358621, -4.570770));
        buildings.add(d03port1);
        Building b03port1 = new Building("B03-PORT1", new LatLng(48.358507, -4.570714));
        buildings.add(b03port1);
        Building a011 = new Building("A01-1", new LatLng(48.359336, -4.569612));
        buildings.add(a011);
        Building a01port1 = new Building("A01-PORT1", new LatLng(48.359245, -4.570091));
        buildings.add(a01port1);
        Building b02 = new Building("B02", new LatLng(48.358731, -4.570634));
        buildings.add(b02);
        markers.add(mMap.addMarker(new MarkerOptions().position(b02.getCenter()).title(b02.getName())));

        Building b021 = new Building("B02-1", new LatLng(48.358646, -4.570444));
        buildings.add(b021);
        Building b04 = new Building("B04", new LatLng(48.358324, -4.569955));
        buildings.add(b04);
        markers.add(mMap.addMarker(new MarkerOptions().position(b04.getCenter()).title(b04.getName())));

        Building b04port1 = new Building("B04-PORT1", new LatLng(48.358301, -4.570234));
        buildings.add(b04port1);
        Building c01 = new Building("c01", new LatLng(48.359361, -4.569059));
        buildings.add(c01);
        markers.add(mMap.addMarker(new MarkerOptions().position(c01.getCenter()).title(c01.getName())));

        Building c011 = new Building("C01-1", new LatLng(48.359169, -4.568922));
        buildings.add(c011);
        Building c02 = new Building("C02", new LatLng(48.359408, -4.569336));
        buildings.add(c02);
        markers.add(mMap.addMarker(new MarkerOptions().position(c02.getCenter()).title(c02.getName())));

        Building c021 = new Building("C02-1", new LatLng(48.359465, -4.569129));
        buildings.add(c021);
        Building c03 = new Building("C03", new LatLng(48.359483, -4.569724));
        buildings.add(c03);
        markers.add(mMap.addMarker(new MarkerOptions().position(c03.getCenter()).title(c03.getName())));

        Building c04 = new Building("C04", new LatLng(48.359214, -4.569528));
        buildings.add(c04);
        markers.add(mMap.addMarker(new MarkerOptions().position(c04.getCenter()).title(c04.getName())));

        Building c05 = new Building("C05", new LatLng(48.358880, -4.569317));
        buildings.add(c05);
        markers.add(mMap.addMarker(new MarkerOptions().position(c05.getCenter()).title(c05.getName())));

        Building c06 = new Building("C06", new LatLng(48.359079, -4.569211));
        buildings.add(c06);
        markers.add(mMap.addMarker(new MarkerOptions().position(c06.getCenter()).title(c06.getName())));

        Building c061 = new Building("C06-1", new LatLng(48.359029, -4.569388));
        buildings.add(c061);
        Building c07 = new Building("C07", new LatLng(48.359050, -4.569601));
        buildings.add(c07);
        markers.add(mMap.addMarker(new MarkerOptions().position(c07.getCenter()).title(c07.getName())));

        Building c071 = new Building("C07-1", new LatLng(48.359089, -4.569456));
        buildings.add(c071);
        Building d011 = new Building("D01-1", new LatLng(48.358997, -4.570821));
        buildings.add(d011);
        Building d021 = new Building("D02-1", new LatLng(48.359236, -4.570392));
        buildings.add(d021);
        Building h01 = new Building("H01", new LatLng(48.358022, -4.569102));
        buildings.add(h01);
        markers.add(mMap.addMarker(new MarkerOptions().position(h01.getCenter()).title(h01.getName())));

        Building h011 = new Building("H01-1", new LatLng(48.357968, -4.569278));
        buildings.add(h011);
        Building h01port1 = new Building("H01-PORT1", new LatLng(48.357966, -4.569062));
        buildings.add(h01port1);
        Building h02 = new Building("H02", new LatLng(48.357921, -4.569506));
        buildings.add(h02);
        markers.add(mMap.addMarker(new MarkerOptions().position(h02.getCenter()).title(h02.getName())));

        Building b03port2 = new Building("B03-PORT2", new LatLng(48.358359, -4.570622));
        buildings.add(b03port2);
        Building p1 = new Building("Point1", new LatLng(48.358476, -4.571031));
        buildings.add(p1);
        Building p2 = new Building("Point2", new LatLng(48.358200, -4.571570));
        buildings.add(p2);
        Building p3 = new Building("Point3", new LatLng(48.358068, -4.570784));
        buildings.add(p3);
        Building p4 = new Building("Point4", new LatLng(48.357601, -4.570492));
        buildings.add(p4);
        Building p5 = new Building("Point5", new LatLng(48.357499, -4.570393));
        buildings.add(p5);
        Building p6 = new Building("Point6", new LatLng(48.357267, -4.571093));
        buildings.add(p6);
        Building i03port1 = new Building("I03-PORT1", new LatLng(48.358157, -4.571329));
        buildings.add(i03port1);
        Building i03port2 = new Building("I03-PORT2", new LatLng(48.357988, -4.571090));
        buildings.add(i03port2);
        Building i03port3 = new Building("I03-PORT3", new LatLng(48.357917, -4.570857));
        buildings.add(i03port3);
        Building i02port1 = new Building("I02-PORT1", new LatLng(48.358094, -4.570567));
        buildings.add(i02port1);
        Building i01port1 = new Building("I01-PORT1", new LatLng(48.357714, -4.570962));
        buildings.add(i01port1);
        Building i01port2 = new Building("I01-PORT2", new LatLng(48.357566, -4.570586));
        buildings.add(i01port2);
        Building i08port1 = new Building("I08-PORT1", new LatLng(48.356634, -4.570318));
        buildings.add(i08port1);
        Building rak = new Building("RAK", new LatLng(48.360229, -4.571922));
        buildings.add(rak);
        markers.add(mMap.addMarker(new MarkerOptions().position(rak.getCenter()).title(rak.getName())));

        Building i08 = new Building("I08", new LatLng(48.356573, -4.570339));
        buildings.add(i08);

        markers.add(mMap.addMarker(new MarkerOptions().position(i08.getCenter()).title(i08.getName())));

        Building p0 = new Building("Point0", new LatLng(48.358501, -4.571803));
        buildings.add(p0);
        Building p7 = new Building("Point7", new LatLng(48.359190, -4.571415));
        buildings.add(p7);
        Building p8 = new Building("Point8", new LatLng(48.360085, -4.570463));
        buildings.add(p8);
        Building p9 = new Building("Point9", new LatLng(48.359560, -4.570132));
        buildings.add(p9);




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
        final LatLng telecom = new LatLng(48.358613, -4.570223);
        //markers.add(mMap.addMarker(new MarkerOptions().position(telecom).title("Marker in telecom"));
        float zoomLevel = 17; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telecom, zoomLevel));


        createBuildings(buildings);

        mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                refreshMap();

                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                String destination = marker.getTitle();

                Room location = findYourself();
                IO.print(location.getName() + " =====================================\n\n\n\n\n");

                if(location != null) {
                    IO.print(location.getName() + " =====================================\n\n\n\n\n");
                    Test t = new Test();
                    List<String> way = t.test(location.getName(), destination);
                    IO.print(way.toString());

                    showBuildingsInOrder(way);

                    IO.print(location.getName());

                    Building b = getBuildingFromName(location.getName());

                    if (b != null) {
                        LatLng locationLatLng = b.getCenter();

                        markers.add(mMap.addMarker(new MarkerOptions().position(locationLatLng).snippet("You are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))));
                    } else {
                        markers.add(mMap.addMarker(new MarkerOptions().position(telecom).title("Couldnt find your location")));
                    }
                }
            }
        });


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
                    }
                    //markers.add(mMap.addMarker(new MarkerOptions().position(b.getCenter()).title(b.getName() + " " + i));
                    route.add(b.getCenter());
                    i++;
                }
            }
        }
        polylines.add(mMap.addPolyline(route));
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

    public void refreshMap(){
        for(Polyline p : polylines){
            p.remove();
        }
        for(Marker m : markers){
            m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
    }
}
