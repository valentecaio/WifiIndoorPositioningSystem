package com.example.andreseidel.interculturel;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.*;

/**
 * Created by caio on 08/12/2016.
 */

public class AppFileManager {
    String filename;
    FileOutputStream outputStream;
    AppCompatActivity delegate;

    public AppFileManager(AppCompatActivity delegate, String filename) {
        this.delegate = delegate;
        this.filename = filename;
        Context context = delegate.getApplication().getApplicationContext();
        File file = new File(context.getFilesDir(), this.filename);
        try {
            this.outputStream = this.delegate.getApplication().openFileOutput(filename, context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        try {
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("", "Directory not created");
        }
        return file;
    }

    public String getPath() {
        String str = "";
        str = getAlbumStorageDir(this.filename).getAbsolutePath().toString();
        return str;
    }
}
