package com.giantcroissant.android_7funs;

import java.util.Date;
import java.util.Locale;

/**
 * Created by liyihao on 15/6/16.
 */
public class CookBook {

    private String id;
    
//    private Cook cook;
    private String url;
    private String image_url;
    private String name;
    private String description;
    private Date uploadTimestamp;
    private int viewedPeople;
    private int collectedPeople;
    private boolean beCollected;

    public CookBook(String id, String name, String description, String url, String image_url, int viewedPeople, int collectedPeople,boolean beCollected)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.image_url = image_url;
        this.uploadTimestamp = new Date(System.currentTimeMillis());
        this.viewedPeople = viewedPeople;
        this.collectedPeople = collectedPeople;
        this.beCollected = beCollected;
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

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public int getViewedPeopleCount() {
        return viewedPeople;
    }

    public void setViewedPeopleCount(int viewedPeople) {
        this.viewedPeople = viewedPeople;
    }

    public int getCollectedPeopleCount() {
        return collectedPeople;
    }

    public void setCollectedPeopleCount(int collectedPeople) {
        this.collectedPeople = collectedPeople;
    }

    public boolean getIsCollected() {
        return beCollected;
    }

    public void setIsCollected(boolean beCollected) {
        this.beCollected = beCollected;
    }
}
