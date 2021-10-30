package com.swufestu.weather.bean;

import com.google.gson.annotations.SerializedName;

public class HoursWeatherBean {

    @SerializedName("hours")
    private String hours;

    @SerializedName("wea_img")
    private String wea_img;

    @SerializedName("tem")
    private String tem;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    @Override
    public String toString() {
        return "HoursWeatherBean{" +
                "hours='" + hours + '\'' +
                ", wea_img='" + wea_img + '\'' +
                ", tem='" + tem + '\'' +
                '}';
    }
}

