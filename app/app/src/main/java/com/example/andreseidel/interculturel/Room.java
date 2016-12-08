package com.example.andreseidel.interculturel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreSeidel on 07/12/16.
 */

public class Room implements Serializable{
    private String name;
    private List<RouterInRoom> routers;

    public Room(String name){
        this.name = name;
        this.routers = new ArrayList<RouterInRoom>();
    }

    public Room(){
        this.routers = new ArrayList<RouterInRoom>();
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


}
