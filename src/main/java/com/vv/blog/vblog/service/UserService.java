package com.vv.blog.vblog.service;

import com.vv.blog.vblog.dao.ArticleDao;
import com.vv.blog.vblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    User register(String username, String password);

    String login(String username, String password);

    User selectByUsername(String username);

    void deleteByUsername(String username);
}
