package com.example.andreseidel.interculturel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreSeidel on 07/12/16.
 */

public class Room implements Serializable{
    private String name;
    private List<RouterInRoom> routers = new ArrayList<RouterInRoom>();

    public Room(String name){
        this.name = name;
    }

    public Room(){}

    public Room RoomFromCSV(String csv){
        String[] data = csv.split(",");
        Room room = new Room(data[0]);
        for(int i=1; i<data.length; i++) {
            RouterInRoom router = new RouterInRoom(
                    data[i],
                    Integer.parseInt(data[i+1]),
                    Integer.parseInt(data[i+2])
            );
            room.add(router);
        }
        return room;
    }

    public void add(RouterInRoom rout){
        for(RouterInRoom router : routers){
            if (router.toString().equals(rout.toString())){
                router.add(rout);
                return;
            }
        }
        routers.add(rout);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RouterInRoom> getRouters() {
        return routers;
    }

    public void setRouters(List<RouterInRoom> routers) {
        this.routers = routers;
    }

    public String toString(){
        String str = "Room " + this.getName() + " \n";

        for (RouterInRoom router : routers){
            str = str + router.toString() + " >> " + router.getMean() + "\n";
        }
        return str;
    }

    public String toCSV(){
        String str = "Room " + this.getName();

        for (RouterInRoom router : routers){
            str += "," + router.toCSV();
        }
        return str;
    }

}
