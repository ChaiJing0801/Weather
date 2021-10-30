package com.swufestu.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swufestu.weather.bean.DayWeatherBean;

import java.util.List;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.FutureViewHolder> {
    private static final String TAG = "FutureAdapter";
    //设置上下文和数据
    private Context context;
    private List<DayWeatherBean> futureWeatherBeans;

    public FutureAdapter(Context context,List<DayWeatherBean> futureBeans) {
        this.context = context;
        this.futureWeatherBeans = futureBeans;
    }

    @NonNull
    @Override
    public FutureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview2_layout,parent,false);
        FutureViewHolder futureViewHolder = new FutureViewHolder(view);
        return futureViewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull FutureViewHolder holder, int position) {
        DayWeatherBean futureWeatherBean = futureWeatherBeans.get(position);
        holder.future_week.setText(futureWeatherBean.getWeek());
        holder.future_wea.setText(futureWeatherBean.getWeather());
        holder.future_high.setText(futureWeatherBean.getHigh());
        holder.future_low.setText(futureWeatherBean.getLow());
        holder.future_img.setImageResource(getImageOfWeather(futureWeatherBean.getImage()));
    }

    @Override
    public int getItemCount() {
        //返回数据集大小
        if(futureWeatherBeans == null){
            return 0;
        }
        return futureWeatherBeans.size();
    }


    class FutureViewHolder extends RecyclerView.ViewHolder{
        //获取控件
        TextView future_week = itemView.findViewById(R.id.future_week);
        TextView future_wea = itemView.findViewById(R.id.future_wea);
        TextView future_high = itemView.findViewById(R.id.future_high);
        TextView future_low = itemView.findViewById(R.id.future_low);
        ImageView future_img = itemView.findViewById(R.id.future_img);
        public FutureViewHolder(@NonNull View itemView) {
            super(itemView);
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
