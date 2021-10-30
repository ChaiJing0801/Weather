package com.swufestu.weather.bean;

import com.google.gson.annotations.SerializedName;

public class TipsBean {

    @SerializedName("title")
    private String title;

    @SerializedName("level")
    private String level;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "TipsBean{" +
                "title='" + title + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
