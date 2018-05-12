package com.vv.blog.vblog.entity;

import java.util.Date;

public class Comment {
    private int cmid;
    private String cmcontent;
    private Date cmdate;
    private String username;
    private int articleid;
    private int stars;

    public int getCmid() {
        return cmid;
    }

    public void setCmid(int cmid) {
        this.cmid = cmid;
    }

    public String getCmcontent() {
        return cmcontent;
    }

    public void setCmcontent(String cmcontent) {
        this.cmcontent = cmcontent;
    }

    public Date getCmdate() {
        return cmdate;
    }

    public void setCmdate(Date cmdate) {
        this.cmdate = cmdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
