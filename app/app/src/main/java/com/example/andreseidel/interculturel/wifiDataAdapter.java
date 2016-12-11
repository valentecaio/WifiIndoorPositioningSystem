package com.example.andreseidel.interculturel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caio on 09/12/2016.
 */

public class wifiDataAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Room> mDataSource;

    public wifiDataAdapter(Context context, ArrayList<Room> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_room_data, parent, false);

        // get elements from xml
        TextView titleTextView = (TextView) rowView.findViewById(R.id.room_data_list_title);
        TextView detailTextView = (TextView) rowView.findViewById(R.id.room_data_list_detail);

        Room room = (Room) getItem(position);

        titleTextView.setText(room.getName());
        //titleTextView.setTextAlignment(1);

        detailTextView.setText(room.getRoutersAsString());

        return rowView;
    }

    public void updateRoomsList(List<Room> newlist) {
        this.mDataSource.clear();
        this.mDataSource.addAll(newlist);
        this.notifyDataSetChanged();
    }
}
