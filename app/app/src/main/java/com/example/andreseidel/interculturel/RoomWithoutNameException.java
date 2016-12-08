package com.example.andreseidel.interculturel;

/**
 * Created by caio on 08/12/2016.
 */
public class RoomWithoutNameException extends Exception {

    public RoomWithoutNameException() {
        IO.print("the room should have a name!");
    }

}
