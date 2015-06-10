package com.giantcroissant.android_7funs;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liyihao on 15/6/10.
 */
public class QuestionRealm extends RealmObject {

    @PrimaryKey
    private String          title;
    private String          content;
    private String          ownerName;
    private String          ownerIconUrl;
    private RealmList<ResponseRealm> responses;
    private Date            createTime;


    @Ignore
    private int             sessionId;

    // Standard getters & setters generated by your IDE…
    public String getTitle() { return title; }
    public void   setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void   setContent(String content) { this.content = content; }
    public String getOwnerName() { return ownerName; }
    public void   setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getOwnerIconUrl() { return ownerIconUrl; }
    public void   setOwnerIconUrl(String ownerIconUrl) { this.ownerIconUrl = ownerIconUrl; }
    public Date   getCreateTime() { return createTime; }
    public void   setCreateTime(Date createTime) { this.createTime = createTime; }
    public int    getSessionId() { return sessionId; }
    public void   setSessionId(int sessionId) { this.sessionId = sessionId; }


    public RealmList<ResponseRealm> getResponses() { return responses; }
    public void   setResponses(RealmList<ResponseRealm> responses) { this.responses = responses; }
}
