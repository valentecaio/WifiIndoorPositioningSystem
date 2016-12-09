package com.example.andreseidel.interculturel;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import java.io.*;

import static android.system.Os.remove;

/**
 * Created by caio on 08/12/2016.
 */

public class AppFileManager {
    private String filePrefix = "room_";
    private String filename;
    private FileOutputStream outputStream;
    private AppCompatActivity delegate;
    private Context context;

    public AppFileManager(AppCompatActivity delegate) {
        this.delegate = delegate;
        this.context = delegate.getApplication().getApplicationContext();
    }

    public AppFileManager(AppCompatActivity delegate, String filename) {
        this.delegate = delegate;
        this.context = delegate.getApplication().getApplicationContext();
        setFileName(filename);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
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

    public ArrayList<String> readAll(){
        // get paths from files
        String path = this.context.getFilesDir().getAbsolutePath();
        IO.print("Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();

        // read files
        ArrayList<String> results = new ArrayList<String>();
        for(File file: files) {
            this.setFileName(file.getName());
            results.add(read());
        }
        return results;
    }

    public boolean deleteAll(){
        String path = this.context.getFilesDir().getAbsolutePath();
        IO.print("Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();

        boolean deleted = true;
        for(File file: files){
            if(file.getName().contains(filePrefix)) {
                IO.print("deleting " + file.getName());
                deleted = file.delete() && deleted;
            }
        }
        return deleted;
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

    public void setFileName(String fileName) {
        this.filename = filePrefix + fileName + ".csv";
    }

    public Room roomFromCSV(String csv) {
        String[] data = csv.split("\n");
        Room room = new Room(data[0]);
        for (int i = 1; i < data.length; i++) {
            String[] line = data[i].split(",");
            if (line.length >= 3) {
                RouterInRoom router = new RouterInRoom(
                        line[0],
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[2])
                );
                room.add(router);
            }
        }
        return room;
    }

    public ArrayList<Room> readAllRoomsAsArray(){
        // read all files
        ArrayList<String> roomsCSV = this.readAll();

        // instantiate all rooms
        ArrayList<Room> rooms = new ArrayList<Room>();
        for(String roomCSV: roomsCSV){
            rooms.add(this.roomFromCSV(roomCSV));
        }
        return rooms;
    }
}
