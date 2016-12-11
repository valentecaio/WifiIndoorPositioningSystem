package com.example.andreseidel.interculturel;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by caio on 11/12/2016.
 */

public class PathNotFound extends Exception {
    public PathNotFound() {
        IO.print("No paths to this place.");
    }
}
