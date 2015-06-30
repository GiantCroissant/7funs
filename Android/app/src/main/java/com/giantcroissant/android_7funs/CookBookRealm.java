package com.giantcroissant.android_7funs;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liyihao on 15/6/30.
 */
public class CookBookRealm extends RealmObject {

    @PrimaryKey
    private String Id;

    private String url;
    private String imageUrl;
    private String name;
    private String description;
    private String ingredient;
    private String sauce;
    private String step;
    private Date uploadTimestamp;
    private int viewedPeopleCount;
    private int collectedPeopleCount;
    private boolean isCollected;
    private String cookId;

    public String getId() { return Id; }
    public void   setId(String Id) { this.Id = Id; }
    public String getUrl() { return url; }
    public void   setUrl(String url) { this.url = url;}
    public String getImageUrl() { return imageUrl; }
    public void   setImageUrl(String imageUrl) { this.imageUrl = imageUrl;}
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void   setDescription(String description) { this.description = description; }
    public String getIngredient() { return ingredient; }
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getSauce() { return sauce; }
    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
    public String getStep() {
        return step;
    }
    public void setStep(String step) {
        this.step = step;
    }
    public Date getUploadTimestamp() {
        return uploadTimestamp;
    }
    public void setUploadTimestamp(Date uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }

    public int getViewedPeopleCount() {
        return viewedPeopleCount;
    }

    public void setViewedPeopleCount(int viewedPeopleCount) {
        this.viewedPeopleCount = viewedPeopleCount;
    }

    public int getCollectedPeopleCount() {
        return collectedPeopleCount;
    }

    public void setCollectedPeopleCount(int collectedPeopleCount) {
        this.collectedPeopleCount = collectedPeopleCount;
    }

    public boolean getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public String getCookId() { return cookId; }
    public void   setCookId(String cookId) { this.cookId = cookId; }
}
