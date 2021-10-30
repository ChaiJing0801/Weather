package com.swufestu.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swufestu.weather.bean.DayWeatherBean;
import com.swufestu.weather.bean.HoursWeatherBean;
import com.swufestu.weather.bean.WeatherBean;

import java.util.ArrayList;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {
    private static final String TAG = "HoursAdapter";
    private Context context;
    private List<HoursWeatherBean> hoursWeatherBeans;

    //构造方法
    public HoursAdapter(Context context,List<HoursWeatherBean> hoursBeans) {
        this.context = context;
        this.hoursWeatherBeans = hoursBeans;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        HoursViewHolder hoursViewHolder = new HoursViewHolder(view);
        return hoursViewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        HoursWeatherBean hoursWeatherBean = hoursWeatherBeans.get(position);
        holder.hours.setText(hoursWeatherBean.getHours().substring(0,2) + ":00");
        holder.wea_img.setImageResource(getImageOfWeather(hoursWeatherBean.getWea_img()));
        holder.tem.setText(hoursWeatherBean.getTem() + "℃");

    }

    @Override
    public int getItemCount() {
        if(hoursWeatherBeans == null){
            return 0;
        }
        return hoursWeatherBeans.size();
    }

    class HoursViewHolder extends RecyclerView.ViewHolder{
        TextView hours,tem;
        ImageView wea_img;
        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            hours = itemView.findViewById(R.id.hours);
            wea_img = itemView.findViewById(R.id.wea_img);
            tem = itemView.findViewById(R.id.tem);

        }
    }

    private int getImageOfWeather(String weaStr){
        int result = 0;
        switch(weaStr){
            case "qing":
                result = R.drawable.qing;
                break;
            case "yin":
                result = R.drawable.yin;
                break;
            case "yu":
                result = R.drawable.yu;
                break;
            case "yun":
                result = R.drawable.yun;
                break;
            case "xue":
                result = R.drawable.xue;
                break;
            case "lei":
                result = R.drawable.lei;
                break;
            case "shachen":
                result = R.drawable.shachen;
                break;
            case "binbao":
                result = R.drawable.bingbao;
                break;
            case "wu":
                result = R.drawable.wu;
                break;
            default:
                result = R.drawable.qing;
                break;
        }
        return result;
    }
}
