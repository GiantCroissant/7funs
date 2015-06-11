package com.giantcroissant.android_7funs;

import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;

/**
 * Created by liyihao on 15/6/10.
 */
public class ResponseOfQuestion {

    private String id;
    private String content;
    private String ownerName;
    private String ownerIconUrl;
    private Date createTime;
    private Question question;

    public ResponseOfQuestion(String id,String content, String ownerName, String ownerIconUrl,Question question) {
        this.id = id;
        this.content = content;
        this.ownerName = ownerName;
        this.ownerIconUrl = ownerIconUrl;
        this.createTime = new Date(System.currentTimeMillis());
        this.question = question;
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

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }
}
