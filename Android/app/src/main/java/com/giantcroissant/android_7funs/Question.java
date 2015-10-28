package com.giantcroissant.android_7funs;


import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liyihao on 15/6/10.
 */
public class Question {

    private String id;
    private String title;
    private String content;
    private String ownerName;
    private String ownerIconUrl;
    private ArrayList<ResponseOfQuestion> responses;
    private Date createTime;

    public Question(String id,String title,String content,String ownerName,String ownerIconUrl)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.ownerName = ownerName;
        this.ownerIconUrl = ownerIconUrl;
        this.createTime = new Date(System.currentTimeMillis());
        this.responses = new ArrayList<ResponseOfQuestion>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return ownerIconUrl;
    }

    public void setOwnerIconUrl(String ownerIconUrl) {
        this.ownerIconUrl = ownerIconUrl;
    }

    public ArrayList<ResponseOfQuestion> getResponseList()
    {
        return responses;
    }

    public int getResponseListCount()
    {
        return responses.size();
    }

    public void setResponseList(ArrayList<ResponseOfQuestion> responses)
    {
        this.responses = responses;
    }

    public void addResponse(ResponseOfQuestion response)
    {
        this.responses.add(response);
    }

    public void removeResponse(ResponseOfQuestion response)
    {
        this.responses.remove(response);
    }

    public void clearResponseList()
    {
        this.responses.clear();
    }
}
