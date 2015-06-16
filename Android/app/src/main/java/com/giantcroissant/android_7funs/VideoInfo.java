package com.giantcroissant.android_7funs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liyihao on 15/6/16.
 */
public class VideoInfo {

    private String id;

    private String url;
    private String icon_url;
    private String name;
    private String description;
    private Date uploadTimestamp;
    private int viewTimes;
    private float timestamp;

    public VideoInfo(String id,String name,String description,String url,String icon_url,int viewTimes,float timestamp)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.icon_url = icon_url;
        this.uploadTimestamp = new Date(System.currentTimeMillis());
        this.viewTimes = viewTimes;
        this.timestamp = timestamp;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUploadTimestamp() {
        return uploadTimestamp;
    }

    public String getLocaleDatetime() {
        return String.format(Locale.getDefault(), "%tF  %<tR", uploadTimestamp);
    }

    // 裝置區域的日期
    public String getLocaleDate() {
        return String.format(Locale.getDefault(), "%tF", uploadTimestamp);
    }

    // 裝置區域的時間
    public String getLocaleTime() {
        return String.format(Locale.getDefault(), "%tR", uploadTimestamp);
    }

    public void setUploadTimestamp(Date uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconUrl() {
        return icon_url;
    }

    public void setIconUrl(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }
}
