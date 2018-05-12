package com.vv.blog.vblog.entity;

import java.util.Date;

public class Article {
    private int id;
    private String auth;
    private String title;
    private String content;
    private String description;
    private Date date;
    private int commentsCnt;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCommentsCnt() {
        return commentsCnt;
    }

    public void setCommentsCnt(int commentsCnt) {
        this.commentsCnt = commentsCnt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
