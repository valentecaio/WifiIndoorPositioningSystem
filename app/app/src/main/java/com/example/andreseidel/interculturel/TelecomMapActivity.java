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
import java.util.List;

public class TelecomMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Building> buildings;

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
        Building b01 = new Building("B01", b1, new LatLng(48.358607, -4.570020));
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
        Building a01 = new Building("A01", a1, new LatLng(48.359229, -4.570029));
        buildings.add(a01);

        PolygonOptions e1 = new PolygonOptions().add(new LatLng(48.358921, -4.570316))
                                                .add(new LatLng(48.359108, -4.569686))
                                                .add(new LatLng(48.358937, -4.569620))
                                                .add(new LatLng(48.358761, -4.570201))
                                                .add(new LatLng(48.358921, -4.570316));
        e1.fillColor(3332);
        Building e01 = new Building("E01", e1, new LatLng(48.358965, -4.569876));
        buildings.add(e01);


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

        List<String> way = new ArrayList<String>();
        way.add("A01");
        way.add("E01");
        way.add("B01");
        way.add("B03");
        showBuildingsInOrder(way);
    }

    public void showBuildingsInOrder(List<String> namesInOrder){
        int i = 1;
        PolylineOptions route = new PolylineOptions();
        for(String name : namesInOrder){
            for (Building b : buildings){
                if(b.getName().equals(name)){
                    b.getBoarders().visible(true);
                    mMap.addPolygon(b.getBoarders());
                    mMap.addMarker(new MarkerOptions().position(b.getCenter()).title("" + i));
                    route.add(b.getCenter());
                    i++;
                }
            }
        }
        mMap.addPolyline(route);
    }
}
