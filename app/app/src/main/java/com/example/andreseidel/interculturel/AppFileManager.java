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
    String appSubDir = "culturel/";
    String fileFormat = ".csv";
    FileOutputStream outputStream;
    AppCompatActivity delegate;
    Context context;

    public AppFileManager(AppCompatActivity delegate, String filename) {
        this.delegate = delegate;
        this.filename = filename + fileFormat;
        this.context = delegate.getApplication().getApplicationContext();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    public File getPathStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), appSubDir);
        if (!file.mkdirs()) {
            Log.e("", "Directory doesn't exist");
        }
        return file;
    }

    public void write(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            IO.print(this, "wrote with sucess the file " + filename);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String read() {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(filename);
            IO.print("reading from " + context.getFilesDir().getAbsolutePath());

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
