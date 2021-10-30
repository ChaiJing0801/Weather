package com.swufestu.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 "city":"成都",
 "update_time":"2021-10-28 14:18:48",
 "data":Array[7]
*/

public class WeatherBean {

    @SerializedName("city")
    private String city;

    @SerializedName("data")
    private List<DayWeatherBean> data;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<DayWeatherBean> getData() {
        return data;
    }

    public void setWeatherInfo(List<DayWeatherBean> weatherInfo) {
        this.data = weatherInfo;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "city='" + city + '\'' +
                ", weatherInfo=" + data +
                '}';
    }
}
