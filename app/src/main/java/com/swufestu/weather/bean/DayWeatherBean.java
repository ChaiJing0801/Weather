package com.swufestu.weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 "day":"25日（星期日）",
 "date":"2021-07-25",
 "week":"星期日",
 "wea":"阴转多云",
 "wea_img":"yun",
 "wea_day":"阴",
 "wea_day_img":"yin",
 "wea_night":"多云",
 "wea_night_img":"yun",
 "tem":"33℃",
 "tem1":"33℃",
 "tem2":"25℃",
 "humidity":"59%",
 "visibility":"15km",
 "win_speed":"3-4级转<3级",
 "air_level":"良",
 "air_tips":"空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！"
 */

public class DayWeatherBean {

    @SerializedName("tem")
    private String temperature;

    @SerializedName("wea_img")
    private String image;

    @SerializedName("date")
    private String date;

    @SerializedName("week")
    private String week;

    @SerializedName("wea")
    private String weather;

    @SerializedName("tem1")
    private String high;

    @SerializedName("tem2")
    private String low;

    @SerializedName("humidity")
    private String humidity;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("air_level")
    private String quality;

    @SerializedName("win_speed")
    private String wind;

    @SerializedName("index")
    private List<TipsBean> tips;

    @SerializedName("hours")
    private List<HoursWeatherBean> hours;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public List<TipsBean> getTips() {
        return tips;
    }

    public void setTips(List<TipsBean> tips) {
        this.tips = tips;
    }

    public List<HoursWeatherBean> getHours() {
        return hours;
    }

    public void setHours(List<HoursWeatherBean> hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "temperature='" + temperature + '\'' +
                ", image='" + image + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", weather='" + weather + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", humidity='" + humidity + '\'' +
                ", visibility='" + visibility + '\'' +
                ", quality='" + quality + '\'' +
                ", wind='" + wind + '\'' +
                ", tips=" + tips +
                ", hours=" + hours +
                '}';
    }
}
