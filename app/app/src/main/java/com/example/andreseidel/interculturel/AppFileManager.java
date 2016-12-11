package com.example.andreseidel.interculturel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by caio on 08/12/2016.
 */

public class AppFileManager {
    private String fileType = ".csv";
    private String name;
    private FileOutputStream outputStream;
    private Context context;

    public AppFileManager(AppCompatActivity delegate) {
        this.context = delegate.getApplication().getApplicationContext();
    }

    public AppFileManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public AppFileManager(AppCompatActivity delegate, String filename) {
        this.context = delegate.getApplication().getApplicationContext();
        this.name = filename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String nameWithType(){
        String str = this.name;
        if(!this.name.contains(this.fileType)) {
            str += this.fileType;
        }
        return str;
    }

    public void write(String data) {
        try {
            String nameToWrite = nameWithType();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(nameToWrite, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            IO.print(this, "wrote with sucess the file " + this.name);
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
            if(formatIsCSV(file)) {
                this.name = (file.getName());
                results.add(read());
            }
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
            if(formatIsCSV(file)) {
                IO.print("deleting " + file.getName());
                deleted = file.delete() && deleted;
            }
        }
        return deleted;
    }

    public boolean deleteFile(){
        String path = this.context.getFilesDir().getAbsolutePath();
        IO.print("Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();

        boolean deleted = true;
        for(File file: files){
            String nameToDelete = nameWithType();
            if(nameToDelete.equals(file.getName()) && formatIsCSV(file)) {
                IO.print("deleting " + file.getName());
                deleted = file.delete();
            }
        }
        return deleted;
    }

    public boolean formatIsCSV(File file){
        return file.getName().contains(fileType);
    }

    public String read() {
        String ret = "";
        try {
            String nameToRead = nameWithType();
            InputStream inputStream = context.openFileInput(nameToRead);
            IO.print("reading from " + nameToRead);

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

    public Room roomFromCSV(String csv) {
        String[] data = csv.split("===");
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
