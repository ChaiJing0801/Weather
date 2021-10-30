package com.swufestu.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CityChoice extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String TAG = "CityChoice";
    //声明相关变量
    public GridView cityGrid;
    EditText input;
    CityAdapter cityAdapter;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choice);
        cityGrid = findViewById(R.id.cityGrid);
        cityGrid.setOnItemClickListener(this);


        Intent intent = getIntent();
        ArrayList<String> cityList = new ArrayList<String> (Arrays.asList(new String[]{"北京","上海",
                "深圳","广州","武汉","长沙","南京","苏州","西安","济南","青岛","沈阳","重庆","郑州","成都",
                "杭州","厦门","天津","长春","兰州","西宁","银川","太原","合肥","贵阳", "昆明","南宁","拉萨",
                "南昌","福州","海口","台北","香港","澳门"}));
        cityAdapter = new CityAdapter(CityChoice.this, R.layout.gridview_layout,cityList);
        cityGrid.setAdapter(cityAdapter);

    }
    public void inputCity(View btn){
        //获取控件内容，保存到Bundle或放入到Extra，返回到调用界面
        input = findViewById(R.id.input);
        String inputGet = input.getText().toString();
        //将数据写入到SP里
        sp = getSharedPreferences("myTile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("save",inputGet);
        editor.commit();
        //将数据传送到MainActivity
        Intent confirmCity = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("input",inputGet);
        Log.i(TAG,"---input的内容为---" + inputGet);
        confirmCity.putExtras(bundle);
        setResult(2,confirmCity);
        //返回到调用页面
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemPosition = cityGrid.getItemAtPosition(i);
        Log.i(TAG,"---itemPosition的内容为---" + itemPosition);
        String choice = (String) itemPosition;
        //将数据写入到SP里
        sp = getSharedPreferences("myTile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("save",choice);
        editor.commit();
        //将数据传送到MainActivity
        Intent choiceCity = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("choice",choice);
        Log.i(TAG,"---choice的内容为---" + choice);
        choiceCity.putExtras(bundle);
        setResult(3,choiceCity);
        //返回到调用页面
        finish();
    }
}