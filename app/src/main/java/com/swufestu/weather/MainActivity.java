package com.swufestu.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.swufestu.weather.bean.DayWeatherBean;
import com.swufestu.weather.bean.HoursWeatherBean;
import com.swufestu.weather.bean.TipsBean;
import com.swufestu.weather.bean.WeatherBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = "MainActivity";

    //声明天气预报相关变量
    private TextView city,weather,temperature,low,high,date,week;
    private ImageView image;
    private RecyclerView hours_weather;
    private RecyclerView future_weather;
    private GridView tips;

    //声明气象信息相关变量
    private TextView humidity,visibility,quality,wind;

    //声明未来小时天气相关变量
    private TextView hours,tem;
    private ImageView wea_img;

    //声明未来七天天气相关变量
    private TextView future_week,future_wea,future_high,future_low;
    private ImageView future_img;

    Handler handler;
    private HoursAdapter hoursAdapter;
    private  FutureAdapter futureAdapter;
    private  TipsAdapter tipsAdapter;

    private String input;
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定控件
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        date = findViewById(R.id.date);
        week = findViewById(R.id.week);
        weather = findViewById(R.id.weather);
        high = findViewById(R.id.high);
        low = findViewById(R.id.low);
        image = findViewById(R.id.image);
        humidity = findViewById(R.id.humidity);
        visibility = findViewById(R.id.visibility);
        quality = findViewById(R.id.quality);
        wind = findViewById(R.id.wind);
        tips = findViewById(R.id.tips);

        //未来小时天气
        hours_weather = findViewById(R.id.hours_weather);
        hours = findViewById(R.id.hours);
        wea_img = findViewById(R.id.wea_img);
        tem = findViewById(R.id.tem);

        //未来7天天气
        future_weather = findViewById(R.id.future_weather);
        future_week = findViewById(R.id.future_week);
        future_wea = findViewById(R.id.future_wea);
        future_high = findViewById(R.id.future_high);
        future_low = findViewById(R.id.future_low);

        SharedPreferences sp = getSharedPreferences("myTile",MODE_PRIVATE);
        String save = sp.getString("save",choice);

        if(save != null){
            getWeatherOfCity(save);
            city.setText(save);
            Log.i(TAG,"---save的内容为---" + save);
        }else{
            getWeatherOfCity(city.getText().toString());
        }

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what ==0){
                    String getWeather = (String) msg.obj;
                    Log.i(TAG,"---主线程接收到了天气数据---" + getWeather);

                    //利用Gson库将json数据转换为JavaBean对象
                    Gson gson = new Gson();
                    WeatherBean weatherBean = gson.fromJson(getWeather,WeatherBean.class);
                    Log.i(TAG,"---解析后的数据---" + weatherBean.toString());

                    //调用更新天气函数
                    updateWeather(weatherBean);

                }
                super.handleMessage(msg);
            }
        };
    }

    private void updateWeather(WeatherBean weatherBean) {
        if(weatherBean == null){
            return;
        }
        //更新天气
        List<DayWeatherBean> dayWeather = weatherBean.getData();
        DayWeatherBean todayWeather = dayWeather.get(0);
        List<HoursWeatherBean> hoursWeather = todayWeather.getHours();
        List<TipsBean> weatherTips = todayWeather.getTips();
        ArrayList<HashMap<String,String>> tipsItems = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<weatherTips.size();i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put(weatherTips.get(i).getTitle().toString(),weatherTips.get(i).getLevel().toString());
            tipsItems.add(map);
            Log.i(TAG,"---生活建议---" + map);

        }
        Log.i(TAG,"---生活建议的ArrayList---" + tipsItems);

        if(todayWeather == null){
            return;
        }
        //设置控件内容
        String str = todayWeather.getTemperature();
        if (str.length()==3) {
            temperature.setText(str.substring(0,2));
        }else{
            temperature.setText(str.substring(0,1));
        }

        image.setImageResource(getImageOfWeather(todayWeather.getImage()));
        date.setText(todayWeather.getDate());
        week.setText(todayWeather.getWeek());
        weather.setText(todayWeather.getWeather());
        high.setText(todayWeather.getHigh());
        low.setText(todayWeather.getLow());

        //气象信息展示
        humidity.setText(todayWeather.getHumidity());
        visibility.setText(todayWeather.getVisibility());
        quality.setText(todayWeather.getQuality());
        wind.setText(todayWeather.getWind());

        //未来小时天气的展示
        hoursAdapter = new HoursAdapter(this,hoursWeather);
        hours_weather.setAdapter(hoursAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        hours_weather.setLayoutManager(layoutManager);

        //未来七天天气的展示
        futureAdapter = new FutureAdapter(this,dayWeather);
        future_weather.setAdapter(futureAdapter);
        LinearLayoutManager layoutManager1 =  new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        future_weather.setLayoutManager(layoutManager1);

        //生活建议展示
        tipsAdapter = new TipsAdapter(this,R.layout.gridview_layout2,tipsItems);
        tips.setAdapter(tipsAdapter);

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

    public void click(View btn){
        ImageButton search = findViewById(R.id.search);
        Intent cityChoice = new Intent(this , CityChoice.class);
        //打开CityChoice的目的是带回数据，不能使用startActivity
        startActivityForResult(cityChoice,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==2){
            Bundle bundle = data.getExtras();
            input = bundle.getString("input");
            city.setText(input);
            getWeatherOfCity(input);
            Log.i(TAG,"---input的内容为---" + input);
        }else if(requestCode==1 && resultCode==3){
            Bundle bundle = data.getExtras();
            choice = bundle.getString("choice");
            city.setText(choice);
            getWeatherOfCity(choice);
            Log.i(TAG,"---choice的内容为---" + choice);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getWeatherOfCity(String selectedCity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                Log.i(TAG,"---Run:选择的城市为---" + selectedCity);
                //使用handler将数据传递给主线程
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = weatherOfCity;
                handler.sendMessage(msg);
            }
        }).start();
    }
}