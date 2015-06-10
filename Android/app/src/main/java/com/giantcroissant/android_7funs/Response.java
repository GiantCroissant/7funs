package com.giantcroissant.android_7funs;

import java.util.Date;

import io.realm.RealmList;

/**
 * Created by liyihao on 15/6/10.
 */
public class Response {

    private String          content;
    private String          ownerName;
    private String          ownerIconUrl;
    private Date createTime;

    public Response(String content,String ownerName,String ownerIconUrl)
    {
        this.content = content;
        this.ownerName = ownerName;
        this.ownerIconUrl = ownerIconUrl;
    }
}
