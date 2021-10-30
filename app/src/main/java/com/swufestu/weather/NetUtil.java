package com.swufestu.weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//网络请求的工具类
public class NetUtil {
    public static final String TAG = "NetUtil";
    public static final String URL = "https://tianqiapi.com/api?version=v1&appid=36646344&appsecret=c1lgQbP9";

    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection http = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //连接网络
        try {
            URL urL = new URL(urlStr);
            http = (HttpURLConnection) urL.openConnection();
            http.setRequestMethod("GET");
            http.setConnectTimeout(5000);

            //从连接中读取数据(二进制)
            InputStream inputStream = http.getInputStream();
            isr = new InputStreamReader(inputStream);
            //二进制流送入缓冲区
            br = new BufferedReader(isr);

            //从缓存区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (http != null) {
                http.disconnect();
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static String getWeatherOfCity(String city) {
        //拼接出获取天气数据的URL
        String weatherUrl = URL + "&city=" + city;
        Log.i(TAG, "----weatherUrl----" + weatherUrl);
        String weatherResult = doGet(weatherUrl);
        Log.d(TAG, "----weatherResult----" + weatherResult);
        return weatherResult;
    }
}