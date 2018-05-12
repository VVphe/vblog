package com.vv.blog.vblog.dao;

public interface TokenManager {
    String createToken(String username);

    boolean getToken(String authentication);

    boolean checkToken(String token);

    void deleteToken(String username);
}
