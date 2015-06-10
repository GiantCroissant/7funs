package com.giantcroissant.android_7funs;

import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;

/**
 * Created by liyihao on 15/6/10.
 */
public class Question {

    private long id;
    private String title;
    private String content;
    private String ownerName;
    private String ownerIconUrl;
    private RealmList<ResponseRealm> responses;
    private Date createTime;

    public Question(String title,String content,String ownerName,String ownerIconUrl)
    {
        this.title = title;
        this.content = content;
        this.ownerName = ownerName;
        this.ownerIconUrl = ownerIconUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getLocaleDatetime() {
        return String.format(Locale.getDefault(), "%tF  %<tR", createTime);
    }

    // 裝置區域的日期
    public String getLocaleDate() {
        return String.format(Locale.getDefault(), "%tF", createTime);
    }

    // 裝置區域的時間
    public String getLocaleTime() {
        return String.format(Locale.getDefault(), "%tR", createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerIconUrl() {
        return ownerName;
    }

    public void setOwnerIconUrl(String ownerIconUrl) {
        this.ownerIconUrl = ownerIconUrl;
    }

    public int GetQuestion()
    {
        return 0;
    }
}
