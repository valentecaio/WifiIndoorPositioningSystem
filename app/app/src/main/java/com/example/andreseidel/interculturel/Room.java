package com.example.andreseidel.interculturel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreSeidel on 07/12/16.
 */

public class Room {
    private String name;
    private List<RouterInRoom> routers;

    public Room(String name){
        this.name = name;
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

    public String toString(){
        String str = "";
        for (RouterInRoom router : routers){
            str = str + router.toString() + " >> " + router.getMean() + "\n";
        }
        return str;
    }


}
