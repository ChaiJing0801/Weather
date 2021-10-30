package com.swufestu.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter {
    private static final String TAG = "CityAdapter";

    public CityAdapter(Context context, int resourse, ArrayList<String> list){
        super(context,resourse,list);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_layout,parent,false);
        }
        String str = (String) getItem(position);
        TextView choice = itemView.findViewById(R.id.choice);
        choice.setText(str);
        return itemView;
    }
}

