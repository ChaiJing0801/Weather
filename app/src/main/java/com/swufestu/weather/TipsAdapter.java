package com.swufestu.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipsAdapter extends ArrayAdapter {
    private static final String TAG = "TipsAdapter";

    public TipsAdapter(Context context, int resource, ArrayList<HashMap<String,String>> list) {
        super(context, resource,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_layout2,parent,false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = itemView.findViewById(R.id.title);
        TextView level = itemView.findViewById(R.id.level);

        for(Map.Entry<String,String> entry:map.entrySet()){
            title.setText(entry.getKey());
            level.setText(entry.getValue());
        }
        return itemView;
    }
}
