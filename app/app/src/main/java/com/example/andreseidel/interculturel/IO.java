package com.example.andreseidel.interculturel;

import android.util.Log;

/**
 * Created by caio on 08/12/2016.
 */

public class IO {
    public static void print(String str){
        Log.e("IO", str);
    }

    public static void print(Object o, String str){
        Log.e("IO " + o.getClass().getName(), str);
    }
}
