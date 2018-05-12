package com.vv.blog.vblog.entity;

public class User {
    private String username;
    private String password;
    private String salt;
//    private String headUrl;
    private String role;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

//    public String getHeadUrl() {
//        return headUrl;
//    }
//
//    public void setHeadUrl(String headUrl) {
//        this.headUrl = headUrl;
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
